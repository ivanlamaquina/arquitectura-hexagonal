package com.example.hexagonal.application.ports.in;

import com.example.hexagonal.domain.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderUseCase {

    public Order createOrder(Order order);

    public List<Order> getAllOrders();

    public Optional<Order> findOrderById(Long orderId);

    public void deleteOrder(Long orderId);
}
