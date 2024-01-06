package com.eazybytes.accounts.service.imp;

import com.eazybytes.accounts.constants.AccountConstants;
import com.eazybytes.accounts.dto.AccountDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entities.Accounts;
import com.eazybytes.accounts.entities.Customer;
import com.eazybytes.accounts.exception.CustomerAlreadyExistsException;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccountMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImp implements IAccountService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    /**
     * @param customerDto
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer= CustomerMapper.mapToCustomer(customerDto,new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already registred  with given MobileNumber"
                    +customerDto.getMobileNumber());
        }

        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
         Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                 ()->new ResourceNotFoundException("customer","mobileNumber",mobileNumber)
        );
         Accounts account=accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                 ()->new ResourceNotFoundException("Account","CustomerId",customer.getCustomerId().toString())
         );
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountMapper.mapToAccountsDto(account,new AccountDto()));
        return customerDto;
    }

    /**
     * @param customerDto
     * @return boolean that indicate if the update details is successful or not
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated=false;
        AccountDto accountDto = customerDto.getAccountsDto();
        System.out.println("1"+accountDto);
        if(accountDto!=null){
            Accounts accounts=accountRepository.findById(accountDto.getAccountNumber()).orElseThrow(
                    ()-> new ResourceNotFoundException("account","AccountNumber",accountDto.getAccountNumber().toString())
            );
            System.out.println("32"+accounts);
            AccountMapper.mapToAccounts(accountDto,accounts);
            accounts=accountRepository.save(accounts);

            Long customerId=accounts.getCustomerId();
            Customer customer=customerRepository.findById(customerId).orElseThrow(
                    ()-> new ResourceNotFoundException("customer","CustomerID",customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated = true;
            System.out.println(isUpdated);
        }
        return  isUpdated ;

    }

    /**
     * @param mobileNumber
     * @return boolean that indicate if the account is successfully deleted or not
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
       Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
               ()->new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
       );
      accountRepository.deleteByCustomerId(customer.getCustomerId());
      customerRepository.deleteById(customer.getCustomerId());
      return true ;
    }


    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount .setCreatedBy("anonymous");
        return newAccount;
    }
}
