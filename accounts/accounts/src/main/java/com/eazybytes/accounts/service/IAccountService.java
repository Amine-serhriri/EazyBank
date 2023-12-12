package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDto;

public interface IAccountService {
    /**
     * @param customerDto
     */
    void createAccount(CustomerDto customerDto);

    /**
     * @param mobileNumber Input mobile number
     * @return Accounts Details based on given mobileNumber
     */
    CustomerDto fetchAccount(String mobileNumber);

    /**
     * @param customerDto
     * @return boolean that indicate if the update details is successful or not
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     * @param mobileNumber
     * @return  boolean that indicate if the account is successfully deleted or not
     */
    boolean deleteAccount(String mobileNumber);
}
