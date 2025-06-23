package com.microservices.loans.service;

import com.microservices.loans.dto.LoansDTO;

public interface LoansService {

    void createLoan(String mobileNumber);

    LoansDTO fetchLoan(String mobileNumber);

    String updateLoan(LoansDTO loansDTO);

    String deleteLoan(String mobileNumber);

}
