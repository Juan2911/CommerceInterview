package com.example.interview.processors.impl;

import com.example.interview.domain.CartDomain;
import com.example.interview.domain.CreateOrderResponse;
import com.example.interview.domain.Domain;
import com.example.interview.entities.Cart;
import com.example.interview.entities.Order;
import com.example.interview.processors.CommerceProcessor;
import com.example.interview.services.mappers.CommerceMapper;
import com.example.interview.services.persistences.DatabaseService;
import com.example.interview.services.validation.ValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class OrderProcessor implements CommerceProcessor {
    private final ValidationService createOrderValidationServiceImpl;
    private final DatabaseService databaseService;
    private final CommerceMapper orderCommerceMapperServiceImpl;
    private final CommerceMapper cartMapper;

    @Override
    public Domain process(Domain request) {
        CreateOrderResponse createOrderResponse = null;
        CartDomain cartDomain = (CartDomain) request;
        createOrderValidationServiceImpl.validate(cartDomain);
        Cart cart = databaseService.getCartByCartId(cartDomain.getCartId());

        if (cart != null) {
            CartDomain cartDomainToCreateOrder = (CartDomain) cartMapper.mapToDomainFromEntity(cart);
            Order order = (Order) orderCommerceMapperServiceImpl.mapToEntityFromDomain(cartDomainToCreateOrder);

            databaseService.saveOrder(order);

            createOrderResponse = (CreateOrderResponse) orderCommerceMapperServiceImpl.mapToDomainFromEntity(order);
        } else {
            //should throw an exception...
        }

        return createOrderResponse;
    }
}
