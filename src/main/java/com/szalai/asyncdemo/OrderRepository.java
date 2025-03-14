package com.szalai.asyncdemo;

import com.szalai.asyncdemo.model.Order;
import com.szalai.asyncdemo.model.dto.OrderRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class OrderRepository {

    private final List<Order> orders = new ArrayList<>();

    public Order save(OrderRequest order) {
        Order orderToSave = new Order(UUID.randomUUID().toString(), order.getName(), order.getPrice());
        orders.add(orderToSave);
        return orderToSave;
    }

    public List<Order> findAll() {
        return orders;
    }
}
