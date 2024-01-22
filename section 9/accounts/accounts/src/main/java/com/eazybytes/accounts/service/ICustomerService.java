package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDetailsDto;
import com.eazybytes.accounts.repository.AccountRepository;
import com.eazybytes.accounts.service.client.CardsFeignClient;

public interface ICustomerService {

    /**
     *
     * @param mobileNumber - Input mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}
