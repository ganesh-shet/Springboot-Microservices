package com.microservices.loans.service;

import com.microservices.loans.Constants.LoansConstants;
import com.microservices.loans.dto.LoansDTO;
import com.microservices.loans.entity.Loans;
import com.microservices.loans.exception.LoanAlreadyExistsException;
import com.microservices.loans.exception.ResourceNotFoundException;
import com.microservices.loans.mapper.LoansMapper;
import com.microservices.loans.repository.LoansRepository;
import com.microservices.loans.service.LoansService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements LoansService {

    private LoansRepository loansRepository;

    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> optionalLoans= loansRepository.findByMobileNumber(mobileNumber);
        if(optionalLoans.isPresent()){
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber "+mobileNumber);
        }
        loansRepository.save(createNewLoan(mobileNumber));
    }


    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }



    @Override
    public LoansDTO fetchLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        return LoansMapper.mapToLoansDTO(loans, new LoansDTO());
    }


    @Override
    public String updateLoan(LoansDTO loansDTO) {
        Loans loans = loansRepository.findByLoanNumber(loansDTO.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "LoanNumber", loansDTO.getLoanNumber()));
        LoansMapper.mapToLoans(loansDTO, loans);
        loansRepository.save(loans);
        return  "Loan details are updated for the mobile number " + loansDTO.getMobileNumber();
    }

    @Override
    public String deleteLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        loansRepository.deleteById(loans.getLoanId());
        return "Loan number " + loans.getLoanNumber() + "deleted successfully for the mobile number " + mobileNumber;
    }


}
