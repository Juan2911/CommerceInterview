package com.example.interview.services.mappers.impl;

import com.example.interview.constants.Constants;
import com.example.interview.domain.CartDomain;
import com.example.interview.domain.CreateOrderResponse;
import com.example.interview.domain.Domain;
import com.example.interview.domain.TotalsDomain;
import com.example.interview.entities.*;
import com.example.interview.services.mappers.CommerceMapper;
import com.example.interview.services.persistences.DatabaseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderCommerceMapperServiceImpl implements CommerceMapper {
    private final DatabaseService databaseService;

    @Override
    public EntityObject mapToEntityFromDomain(Domain request) {
        CartDomain cartDomain = (CartDomain) request;

        int products = 0;
        int coffeeQuantity = 0;
        double shipping = cartDomain.getShipping();
        int equipmentQuantity = 0;
        double totalWithoutdiscounts = 0;
        double totalValue;

        Cart cart = databaseService.getCartByCartId(cartDomain.getCartId());
        List<CartDetail> cartDetails = cart.getCartDetails();

        for (CartDetail cartDetail : cartDetails) {
            if (Constants.COFFEE_CATEGORY.equalsIgnoreCase(cartDetail.getProduct().getCategory())) {
                coffeeQuantity += cartDetail.getQuantity();
            }
            if (Constants.EQUIPMENT_CATEGORY.equalsIgnoreCase(cartDetail.getProduct().getCategory())) {
                equipmentQuantity += cartDetail.getQuantity();
            }

            products += cartDetail.getQuantity();
            totalWithoutdiscounts += cartDetail.getSubTotal();

        }

        totalValue = totalWithoutdiscounts;

        if (coffeeQuantity > 2) {
            products += 1;
        }

        if (equipmentQuantity > 3) {
            shipping = 0;
        }

        if (totalWithoutdiscounts > 70) {
            totalValue = totalValue - (totalValue * 0.1);
        }

        return Order.builder()
                .discounts(totalWithoutdiscounts - totalValue)
                .orderTotal(totalValue)
                .products(products)
                .shipping(shipping)
                .build();
    }

    @Override
    public Domain mapToDomainFromEntity(EntityObject entityObject) {
        Order order = (Order) entityObject;
        TotalsDomain totalsDomain = TotalsDomain.builder()
                .discounts(order.getDiscounts())
                .products(order.getProducts())
                .order(order.getOrderTotal())
                .shipping(order.getShipping())
                .build();


        return CreateOrderResponse.builder()
                .orderId(order.getId())
                .totals(totalsDomain)
                .build();
    }
}
