package com.microservices.loans.mapper;

import com.microservices.loans.entity.Loans;
import com.microservices.loans.dto.LoansDTO;

public class LoansMapper {

    public static LoansDTO mapToLoansDTO(Loans loans, LoansDTO loansDTO) {
        loansDTO.setLoanNumber(loans.getLoanNumber());
        loansDTO.setLoanType(loans.getLoanType());
        loansDTO.setMobileNumber(loans.getMobileNumber());
        loansDTO.setTotalLoan(loans.getTotalLoan());
        loansDTO.setAmountPaid(loans.getAmountPaid());
        loansDTO.setOutstandingAmount(loans.getOutstandingAmount());
        return loansDTO;
    }

    public static Loans mapToLoans(LoansDTO loansDTO, Loans loans) {
        loans.setLoanNumber(loansDTO.getLoanNumber());
        loans.setLoanType(loansDTO.getLoanType());
        loans.setMobileNumber(loansDTO.getMobileNumber());
        loans.setTotalLoan(loansDTO.getTotalLoan());
        loans.setAmountPaid(loansDTO.getAmountPaid());
        loans.setOutstandingAmount(loansDTO.getOutstandingAmount());
        return loans;
    }

}
