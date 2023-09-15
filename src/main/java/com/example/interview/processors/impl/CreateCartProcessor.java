package com.example.interview.processors.impl;

import com.example.interview.domain.CartDomain;
import com.example.interview.domain.Domain;
import com.example.interview.entities.Cart;
import com.example.interview.processors.CommerceProcessor;
import com.example.interview.services.mappers.CommerceMapper;
import com.example.interview.services.persistences.DatabaseService;
import com.example.interview.services.validation.ValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateCartProcessor implements CommerceProcessor {

    private final ValidationService addCartValidation;
    private final CommerceMapper cartMapper;
    private DatabaseService databaseService;

    @Override
    public Domain process(Domain request) {
        CartDomain cartRequest = (CartDomain) request;
        addCartValidation.validate(cartRequest);
        Cart cart = (Cart) cartMapper.mapToEntityFromDomain(cartRequest);
        Cart cartDatabase = databaseService.saveCart(cart);
        CartDomain cartResponse = (CartDomain) cartMapper.mapToDomainFromEntity(cartDatabase);
        return cartResponse;
    }
}
