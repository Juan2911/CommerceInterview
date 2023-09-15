package com.example.interview.controllers;

import com.example.interview.domain.CartDomain;
import com.example.interview.domain.ModifyQuantityDomain;
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
public class ProductsController {
    private final CommerceProcessor addProductCommerceProcessorImpl;

    @PostMapping(value = "/addProducts", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartDomain> modifyQuantityController(@RequestBody CartDomain request) {
        CartDomain addCartResponse = (CartDomain) addProductCommerceProcessorImpl.process(request);
        log.info("Response for Modify Quantity: {}", addCartResponse);

        return new ResponseEntity<>(addCartResponse, HttpStatus.OK);
    }
}
