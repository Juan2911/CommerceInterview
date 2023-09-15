package com.example.interview.mappers;

import com.example.interview.Utils.TestObjectBuilder;
import com.example.interview.domain.CartDomain;
import com.example.interview.entities.Cart;
import com.example.interview.entities.Order;
import com.example.interview.services.mappers.impl.OrderCommerceMapperServiceImpl;
import com.example.interview.services.persistences.DatabaseService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class OrderCommerceMapperServiceImplTest {
    private OrderCommerceMapperServiceImpl orderCommerceMapperService;
    @Mock
    private DatabaseService databaseServiceMock;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        Cart cartWithProducts = TestObjectBuilder.buildCartWithDifferentProductsEntityModifiedQuantity();
        orderCommerceMapperService = new OrderCommerceMapperServiceImpl(databaseServiceMock);

        when(databaseServiceMock.getCartByCartId(1)).thenReturn(cartWithProducts);
    }


    @Test
    public void shouldSaveACartWithProductsSuccessful() {
        Order orderToCompare = Order.builder()
                .products(11)
                .shipping(0)
                .discounts(15.5)
                .orderTotal(139.5)
                .build();
        CartDomain cartDomain = CartDomain.builder()
                .cartId(1)
                .build();
        Order order = (Order) orderCommerceMapperService.mapToEntityFromDomain(cartDomain);
        Assert.assertEquals(orderToCompare, order);
    }
}
