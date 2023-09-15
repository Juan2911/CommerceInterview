package com.example.interview.controllers;

import com.example.interview.domain.CartDomain;
import com.example.interview.domain.CreateOrderResponse;
import com.example.interview.processors.CommerceProcessor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@AllArgsConstructor
@Slf4j
public class OrderController {
    private final CommerceProcessor orderProcessor;

    @PostMapping(value = "/createOrder", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateOrderResponse> modifyQuantityController(@RequestBody CartDomain request) {
        CreateOrderResponse createOrderResponse = (CreateOrderResponse) orderProcessor.process(request);
        log.info("Response for Create Order: {}", createOrderResponse);

        return new ResponseEntity<>(createOrderResponse, HttpStatus.OK);
    }
}
