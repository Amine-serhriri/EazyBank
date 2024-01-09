package com.easybytes.loans.service;

import com.easybytes.loans.dto.LoansDto;

public interface ILoansService {
    /**
     *
     * @param mobileNumber - mobile number of the customer
     */
    void createLoan(String mobileNumber);

    /**
     *
     * @param mobileNumber- Input mobile Number
     * @Return Loan details based on a iven mobileNumber
     */
    LoansDto fetchLoan(String mobileNumber);

    /**
     *
     * @param loansDto- LoansDto object
     * @return boolean indicating if the update of cards is successful or not
     */
    boolean updateLoan(LoansDto loansDto);

    /**
     *
     * @param mobileNumber - Input mobileNumber
     * @return boolean indicating if the delete of loan details is successful or not
     */
    boolean deleteLoan(String mobileNumber);

}
