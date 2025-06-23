package com.microservices.accounts.Mapper;

import com.microservices.accounts.DTO.AccountsDTO;
import com.microservices.accounts.entity.Accounts;

public class AccountsMapper {

    public static AccountsDTO mapToAccountsDTO(Accounts accounts, AccountsDTO accountsDTO) {
        accountsDTO.setAccountNumber(accounts.getAccountNumber());
        accountsDTO.setAccountType(accounts.getAccountType());
        accountsDTO.setBranchAddress(accounts.getBranchAddress());
        return accountsDTO;
    }

    public static Accounts mapToAccounts(AccountsDTO accountsDTO, Accounts accounts) {
        accounts.setAccountNumber(accountsDTO.getAccountNumber());
        accounts.setAccountType(accountsDTO.getAccountType());
        accounts.setBranchAddress(accountsDTO.getBranchAddress());
        return accounts;
    }

}
