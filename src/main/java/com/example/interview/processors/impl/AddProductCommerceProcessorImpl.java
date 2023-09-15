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
public class AddProductCommerceProcessorImpl implements CommerceProcessor {

    private final ValidationService addProductsValidationServiceImpl;
    private final DatabaseService databaseService;
    private final CommerceMapper cartMapper;

    @Override
    public Domain process(Domain request) {
        CartDomain domain = (CartDomain) request;
        addProductsValidationServiceImpl.validate(domain);
        Cart cart = databaseService.getCartByCartId(domain.getCartId());
        if (cart == null) {
            //throw an exception
        }
        Cart cartMapped = (Cart) cartMapper.mapToEntityFromDomain(domain);
        cart.setCartDetails(cartMapped.getCartDetails());

        databaseService.saveCart(cart);

        CartDomain response = (CartDomain) cartMapper.mapToDomainFromEntity(cart);

        return response;
    }
}
