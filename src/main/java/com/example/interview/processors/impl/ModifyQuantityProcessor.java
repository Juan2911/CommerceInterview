package com.example.interview.processors.impl;

import com.example.interview.domain.CartDomain;
import com.example.interview.domain.Domain;
import com.example.interview.domain.ModifyQuantityDomain;
import com.example.interview.entities.Cart;
import com.example.interview.entities.CartDetail;
import com.example.interview.entities.Product;
import com.example.interview.processors.CommerceProcessor;
import com.example.interview.services.mappers.CommerceMapper;
import com.example.interview.services.persistences.DatabaseService;
import com.example.interview.services.validation.ValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ModifyQuantityProcessor implements CommerceProcessor {

    private final ValidationService modifyQuantityValidation;
    private final DatabaseService databaseService;
    private final CommerceMapper cartMapper;

    @Override
    public Domain process(Domain request) {
        ModifyQuantityDomain modifyQuantityDomain = (ModifyQuantityDomain) request;
        modifyQuantityValidation.validate(modifyQuantityDomain);
        Cart cart = databaseService.getCartByCartId(modifyQuantityDomain.getCartId());

        if (cart == null) {
            //through and api exception and captured by advice controller beacuse dont exist the cart
        }

        List<CartDetail> cartDetailList = cart.getCartDetails();
        if (!cartDetailList.isEmpty()) {
            for (CartDetail cartDetailDatabase : cartDetailList) {
                Product productCartDetail = cartDetailDatabase.getProduct();
                if (modifyQuantityDomain.getProductName().equalsIgnoreCase(productCartDetail.getName())) {
                    cartDetailDatabase.setQuantity(modifyQuantityDomain.getQuantity());
                    cartDetailDatabase.setSubTotal(modifyQuantityDomain.getQuantity() * productCartDetail.getPrice());
                    break;
                }
            }
        } else {
            //throw an api exception because cart don't have any products
        }

        Cart cartSaved = databaseService.saveCart(cart);
        CartDomain cartResponse = (CartDomain) cartMapper.mapToDomainFromEntity(cartSaved);
        return cartResponse;
    }
}
