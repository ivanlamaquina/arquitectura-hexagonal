package com.example.hexagonal.adapters.out.persistence;

import com.example.hexagonal.application.ports.out.OrderPort;
import com.example.hexagonal.domain.model.Order;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class JpaOrderPort implements OrderPort {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Order save(Order order) {
        if (order.getId() == null) {
            entityManager.persist(order);
            return order;
        } else {
            return entityManager.merge(order);
        }
    }

    @Override
    public Optional<Order> findById(Long orderId) {
        return Optional.ofNullable(entityManager.find(Order.class, orderId));
    }

    @Override
    public List<Order> findAll() {
        return entityManager.createQuery("SELECT o FROM Order o", Order.class).getResultList();
    }

    @Override
    public void deleteById(Long orderId) {
        findById(orderId).ifPresent(entityManager::remove);
    }
}
