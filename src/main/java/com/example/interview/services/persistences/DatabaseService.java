package com.example.interview.services.persistences;

import com.example.interview.entities.Cart;
import com.example.interview.entities.Order;

public interface DatabaseService {
    Cart saveCart(Cart cart);

    Cart getCartByCartId(long id);

    Order saveOrder(Order order);
}
