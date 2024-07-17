package com.example.hexagonal.application.service;

import com.example.hexagonal.application.ports.in.OrderUseCase;
import com.example.hexagonal.application.ports.out.OrderPort;
import com.example.hexagonal.domain.model.Order;
import com.example.hexagonal.adapters.out.persistence.JpaOrderPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class OrderService implements OrderUseCase {


    private OrderPort orderPort;

    @Inject
    public OrderService(JpaOrderPort orderRepository) {
        this.orderPort = orderRepository;
    }

    @Transactional
    @Override
    public Order createOrder(Order order) {
        orderPort.save(order);
        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderPort.findAll();
    }

    @Override
    public Optional<Order> findOrderById(Long orderId) {

        return orderPort.findById(orderId);
    }

    @Transactional
    @Override
    public void deleteOrder(Long orderId) {
        orderPort.deleteById(orderId);
    }
}
