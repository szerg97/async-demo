package com.szalai.asyncdemo.repository;

import com.szalai.asyncdemo.model.Order;
import com.szalai.asyncdemo.model.dto.AddOrderRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class OrderRepository {

    private final List<Order> orders = new ArrayList<>();

    public Order save(AddOrderRequest order) {
        final Order orderToSave = new Order(UUID.randomUUID().toString(), order.getName(), order.getPrice());
        orders.add(orderToSave);
        return orderToSave;
    }

    public List<Order> findAll() {
        return orders;
    }
}
