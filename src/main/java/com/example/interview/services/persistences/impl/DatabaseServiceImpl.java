package com.example.interview.services.persistences.impl;

import com.example.interview.entities.Cart;
import com.example.interview.entities.Order;
import com.example.interview.services.persistences.DatabaseService;
import com.example.interview.services.repositories.CartRepository;
import com.example.interview.services.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DatabaseServiceImpl implements DatabaseService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    @Override
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart getCartByCartId(long id) {
        return cartRepository.getCartById(id);
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}
