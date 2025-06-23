package com.microservices.accounts.service;

import com.microservices.accounts.DTO.CustomerDTO;
import com.microservices.accounts.entity.Customer;

public interface AccountsService{
    CustomerDTO createAccount(CustomerDTO customerDto);

    CustomerDTO getAccounts(String mobileNumber);

    boolean updateAccount(CustomerDTO customerDTO);

    boolean deleteAccount(String mobileNumber);
}
