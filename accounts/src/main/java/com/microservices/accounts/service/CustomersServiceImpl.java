package com.microservices.accounts.service;

import com.microservices.accounts.DTO.AccountsDTO;
import com.microservices.accounts.DTO.CardsDTO;
import com.microservices.accounts.DTO.CustomerDetailsDTO;
import com.microservices.accounts.DTO.LoansDTO;
import com.microservices.accounts.Mapper.AccountsMapper;
import com.microservices.accounts.Mapper.CustomerMapper;
import com.microservices.accounts.entity.Accounts;
import com.microservices.accounts.entity.Customer;
import com.microservices.accounts.exceptions.ResourceNotFoundException;
import com.microservices.accounts.repository.AccountsRepository;
import com.microservices.accounts.repository.CustomerRepository;
import com.microservices.accounts.service.Client.CardsFeignClient;
import com.microservices.accounts.service.Client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements CustomersService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    @Override
    public CustomerDetailsDTO fetchCustomerDetails(String mobileNumber,String correlationId) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDTO customerDetailsDTO = CustomerMapper.mapToCustomerDetailsDTO(customer, new CustomerDetailsDTO());
        customerDetailsDTO.setAccountsDTO(AccountsMapper.mapToAccountsDTO(accounts, new AccountsDTO()));

        ResponseEntity<LoansDTO> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationId, mobileNumber);
        customerDetailsDTO.setLoansDTO(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDTO> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationId, mobileNumber);
        customerDetailsDTO.setCardsDTO(cardsDtoResponseEntity.getBody());

        return customerDetailsDTO;

    }
}