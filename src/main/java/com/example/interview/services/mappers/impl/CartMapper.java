package com.example.interview.services.mappers.impl;


import com.example.interview.domain.CartDomain;
import com.example.interview.domain.CartDetailRequest;
import com.example.interview.domain.Domain;
import com.example.interview.entities.Cart;
import com.example.interview.entities.CartDetail;
import com.example.interview.entities.EntityObject;
import com.example.interview.entities.Product;
import com.example.interview.services.mappers.CommerceMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartMapper implements CommerceMapper {

    @Override
    public EntityObject mapToEntityFromDomain(Domain request) {
        CartDomain addCartRequest = (CartDomain) request;

        List<CartDetail> cartDetailList = new ArrayList<>();
        List<CartDetailRequest> cartDetailRequests = addCartRequest.getProducts();

        if(!cartDetailRequests.isEmpty()) {
            for (CartDetailRequest cartDetailRequest : cartDetailRequests) {
                double price = cartDetailRequest.getPrice();
                int quantity = cartDetailRequest.getQuantity();

                Product product = Product.builder()
                        .category(cartDetailRequest.getCategory())
                        .name(cartDetailRequest.getProductName())
                        .price(price)
                        .build();

                CartDetail cartDetail = CartDetail.builder()
                        .product(product)
                        .quantity(quantity)
                        .subTotal(price * quantity)
                        .build();

                cartDetailList.add(cartDetail);
            }
        }


        return Cart.builder()
                .cartDetails(cartDetailList)
                .userId(addCartRequest.getUserId())
                .build();
    }

    @Override
    public Domain mapToDomainFromEntity(EntityObject entityObject) {
        Cart cart = (Cart) entityObject;
        List<CartDetail> cartDetailLis = cart.getCartDetails();
        List<CartDetailRequest> products = new ArrayList<>();

        for (CartDetail cartDetail: cartDetailLis) {
            Product product = cartDetail.getProduct();

            CartDetailRequest cartDetailRequest = CartDetailRequest.builder()
                    .category(product.getCategory())
                    .price(product.getPrice())
                    .quantity(cartDetail.getQuantity())
                    .productName(product.getName())
                    .build();

            products.add(cartDetailRequest);
        }

        return CartDomain.builder()
                .cartId(cart.getId())
                .products(products)
                .userId(cart.getUserId())
                .build();
    }
}
