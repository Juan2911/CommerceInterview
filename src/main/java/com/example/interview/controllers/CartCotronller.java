package com.example.interview.controllers;

import com.example.interview.domain.CartDomain;
import com.example.interview.domain.ModifyQuantityDomain;
import com.example.interview.processors.CommerceProcessor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@AllArgsConstructor
@Slf4j
public class CartCotronller {
    private CommerceProcessor createCartProcessor;
    private CommerceProcessor modifyQuantityProcessor;

    @PostMapping(value = "/createCart", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartDomain> createCartController(@RequestBody CartDomain request) {
        CartDomain addCartResponse = (CartDomain) createCartProcessor.process(request);
        log.info("Response for Cart: {}", addCartResponse);

        return new ResponseEntity<>(addCartResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/modifyQuantity", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartDomain> modifyQuantityController(@RequestBody ModifyQuantityDomain request) {
        CartDomain addCartResponse = (CartDomain) modifyQuantityProcessor.process(request);
        log.info("Response for Modify Quantity: {}", addCartResponse);

        return new ResponseEntity<>(addCartResponse, HttpStatus.OK);
    }
}
