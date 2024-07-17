package com.example.hexagonal.application.ports.out;

import com.example.hexagonal.domain.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderPort {

    Order save(Order order);
    Optional<Order> findById(Long orderId);
    List<Order> findAll();
    void deleteById(Long orderId);
}
