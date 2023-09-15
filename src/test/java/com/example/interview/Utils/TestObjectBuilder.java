package com.example.interview.Utils;

import com.example.interview.domain.CartDetailRequest;
import com.example.interview.domain.CartDomain;
import com.example.interview.domain.ModifyQuantityDomain;
import com.example.interview.entities.Cart;
import com.example.interview.entities.CartDetail;
import com.example.interview.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class TestObjectBuilder {
    public static CartDomain buildCartWithProductsDomain() {
        CartDetailRequest cartDetailRequest = buildCartDetailRequest();
        List<CartDetailRequest> cartDetailRequests = new ArrayList<>();
        cartDetailRequests.add(cartDetailRequest);

        return CartDomain.builder()
                .products(cartDetailRequests)
                .cartId(1)
                .build();
    }

    public static CartDetailRequest buildCartDetailRequest() {
        return CartDetailRequest.builder()
                .productName("Cappuccino")
                .quantity(1)
                .price(1)
                .category("Coffee")
                .build();
    }

    public static Cart buildCartSavedDatabase() {
        return Cart.builder()
                .userId("anUserId")
                .id(1)
                .build();
    }

    public static Cart buildCartWithProductsEntity() {
        CartDetail cartDetail = buildCoffee(1);
        List<CartDetail> cartDetailList = new ArrayList<>();

        cartDetailList.add(cartDetail);

        return Cart.builder()
                .userId("anUserId")
                .id(1)
                .cartDetails(cartDetailList)
                .build();
    }

    public static Cart buildCartWithProductsEntityModifiedQuantity() {
        CartDetail cartDetail = buildCoffee(5);
        List<CartDetail> cartDetailList = new ArrayList<>();

        cartDetailList.add(cartDetail);

        return Cart.builder()
                .userId("anUserId")
                .id(1)
                .cartDetails(cartDetailList)
                .build();
    }

    public static Cart buildCartWithDifferentProductsEntityModifiedQuantity() {
        CartDetail coffee = buildCoffee(5);
        CartDetail equipment = buildEquipment(5);
        List<CartDetail> cartDetailList = new ArrayList<>();

        cartDetailList.add(coffee);
        cartDetailList.add(equipment);
        return Cart.builder()
                .userId("anUserId")
                .id(1)
                .cartDetails(cartDetailList)
                .build();
    }

    public static CartDetail buildCoffee(int quantity) {
        Product product = buildACappuccinoProduct();

        return CartDetail.builder()
                .subTotal(product.getPrice() * quantity)
                .product(product)
                .quantity(quantity)
                .build();
    }

    public static CartDetail buildEquipment(int quantity) {
        Product product = buildAnEquipmentProduct();

        return CartDetail.builder()
                .subTotal(quantity * product.getPrice())
                .product(product)
                .quantity(quantity)
                .build();
    }

    public static Product buildACappuccinoProduct() {
        return Product.builder()
                .price(1)
                .name("Cappuccino")
                .category("Coffee")
                .build();
    }

    public static Product buildAnEquipmentProduct() {
        return Product.builder()
                .price(30)
                .name("aName")
                .category("Equipment")
                .build();
    }

    public static ModifyQuantityDomain buildModifyQuantityDomain() {
        return ModifyQuantityDomain.builder()
                .quantity(5)
                .cartId(1)
                .productName("Cappuccino")
                .build();
    }
}
