package com.microservices.accounts.DTO;

public record AccountsMsgDTO(Long accountNumber, String name, String email, String mobileNumber) {
}
