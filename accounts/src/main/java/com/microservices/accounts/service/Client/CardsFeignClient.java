package com.microservices.accounts.service.Client;

import com.microservices.accounts.DTO.CardsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="cards", fallback = CardsFallback.class)
public interface CardsFeignClient {

    @GetMapping(value = "/api/cards/fetch", consumes = "application/json")
    public ResponseEntity<CardsDTO> fetchCardDetails(@RequestHeader("springbank-correlation-id") String correlationId,
                                                     @RequestParam String mobileNumber);
}
