package com.microservices.gateway.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {
    @RequestMapping("/contactSupport")
    public Mono<String> contactSupport() {
        return Mono.just("Service is taking longer than expected. Please try again later or contact support team.");
    }
}
