package com.example.interview.services.validation.impl;

import com.example.interview.constants.Constants;
import com.example.interview.domain.CartDomain;
import com.example.interview.domain.Domain;
import com.example.interview.services.validation.ValidationService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class AddProductsValidationServiceImpl implements ValidationService {
    @Override
    public void validate(Domain request) {
        CartDomain addCartRequest = (CartDomain) request;

        try {
            Assert.notNull(addCartRequest.getCartId(), Constants.CART_ID_NULL_MESSAGE);
            Assert.notNull(addCartRequest.getProducts(), Constants.PRODUCTS_NULL_MESSAGE);
        } catch (Exception e) {
            //Throw and api exception and captured by advice controller
            System.out.println();
        }
    }
}
