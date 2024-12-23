package ru.app.repository;

import ru.app.model.Order;

import java.util.List;

public interface OrderRepository {
    Order save(Order order);
    List<Order> findAll();
}
