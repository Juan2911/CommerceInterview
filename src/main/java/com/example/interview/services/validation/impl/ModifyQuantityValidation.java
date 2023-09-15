package com.example.interview.services.validation.impl;

import com.example.interview.constants.Constants;
import com.example.interview.domain.Domain;
import com.example.interview.domain.ModifyQuantityDomain;
import com.example.interview.services.validation.ValidationService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class ModifyQuantityValidation implements ValidationService {
    @Override
    public void validate(Domain request) {
        ModifyQuantityDomain modifyQuantityDomain = (ModifyQuantityDomain) request;

        try {
            Assert.notNull(modifyQuantityDomain.getCartId(), Constants.CART_ID_NULL_MESSAGE);
            Assert.notNull(modifyQuantityDomain.getQuantity(), Constants.QUANTITY_NULL_MESSAGE);
            Assert.hasText(modifyQuantityDomain.getProductName(), Constants.PRODUCT_NAME_NULL_MESSAGE);
        } catch (Exception e) {
            //Throw and api exception and captured by advice controller
            System.out.println();
        }
    }
}
