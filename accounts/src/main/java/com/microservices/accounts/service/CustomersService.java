package com.microservices.accounts.service;

import com.microservices.accounts.DTO.CustomerDetailsDTO;

public interface CustomersService {

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    CustomerDetailsDTO fetchCustomerDetails(String mobileNumber);
}
