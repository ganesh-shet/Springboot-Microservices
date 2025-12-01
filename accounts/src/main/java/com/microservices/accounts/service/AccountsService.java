package com.microservices.accounts.service;

import com.microservices.accounts.DTO.CustomerDTO;

public interface AccountsService{
    void createAccount(CustomerDTO customerDTO);

    CustomerDTO getAccounts(String mobileNumber);

    boolean updateAccount(CustomerDTO customerDTO);

    boolean deleteAccount(String mobileNumber);

    boolean updateCommunicationStatus(Long accountNumber);
}
