package com.szalai.asyncdemo.repository;

import com.szalai.asyncdemo.model.Order;
import com.szalai.asyncdemo.model.dto.AddOrderRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class OrderRepository {

    private static final List<Order> orders = new ArrayList<>();

    static {
        orders.addAll(List.of(
                new Order(UUID.randomUUID().toString(), "Order-1", 23.0),
                new Order(UUID.randomUUID().toString(), "Order-2", 54.5),
                new Order(UUID.randomUUID().toString(), "Order-3", 32.2)
        ));
    }

    public Order save(AddOrderRequest order) {
        final Order orderToSave = new Order(UUID.randomUUID().toString(), order.getName(), order.getPrice());
        orders.add(orderToSave);
        return orderToSave;
    }

    public List<Order> findAll() {
        return orders;
    }
}
