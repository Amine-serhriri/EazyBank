package com.easybytes.loans.service.Imp;

import com.easybytes.loans.constants.LoansConstants;
import com.easybytes.loans.dto.LoansDto;
import com.easybytes.loans.entities.Loans;
import com.easybytes.loans.exception.LoanAlreadyExistsException;
import com.easybytes.loans.exception.ResourceNotFoundException;
import com.easybytes.loans.mappers.LoansMapper;
import com.easybytes.loans.reposiitory.LoansRepository;
import com.easybytes.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImp implements ILoansService {

    private LoansRepository loansRepository ;

    /**
     * @param mobileNumber - mobile number of the customer
     */
    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> optionalLoans = loansRepository.findByMobileNumber(mobileNumber);
        if(optionalLoans.isPresent()){
            throw new LoanAlreadyExistsException("Loans Already registered with given mobile number "+mobileNumber);
        }
        loansRepository.save(createNewLoan(mobileNumber));
    }

    private Loans createNewLoan(String mobileNumber){
        Long randomLoanNumber=100000000000L  + new Random().nextInt(900000000);
        Loans loans =Loans.builder()
                .loanNumber(Long.toString(randomLoanNumber))
                .mobileNumber(mobileNumber)
                .loanType(LoansConstants.HOME_LOAN)
                .totalLoan(LoansConstants.NEW_LOAN_LIMIT)
                .amountPaid(0)
                .outstandingAmount(LoansConstants.NEW_LOAN_LIMIT)
                .build();
        return loans ;
    }

    /**
     * @param mobileNumber - Input mobile Number
     * @Return Loan details based on  given mobileNumber
     */
    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans loans =loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        return LoansMapper.mapToLoansDto(loans,new LoansDto());
    }

    /**
     * @param loansDto - LoansDto object
     * @return boolean indicating if the update of cards is successful or not
     */
    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans loans=loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                ()-> new ResourceNotFoundException("Loan","LoanNumber",loansDto.getLoanNumber())
        );
        LoansMapper.mapToLoans(loansDto,loans);
        loansRepository.save(loans);
        return true;
    }

    /**
     * @param mobileNumber - Input mobileNumber
     * @return boolean indicating if the delete of loan details is successful or not
     */
    @Override
    public boolean deleteLoan(String mobileNumber) {
       Loans loans =loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
               ()->new ResourceNotFoundException("Loan","mobileNumber",mobileNumber)
       );
       loansRepository.deleteById(loans.getLoanId());
       return  true ;
    }
}
