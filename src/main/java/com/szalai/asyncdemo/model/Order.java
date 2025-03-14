package com.szalai.asyncdemo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Order {
    private String orderId;
    private String name;
    private Double price;
    private Boolean dispatched = false;

    public Order(String orderId, String name, Double price) {
        this.orderId = orderId;
        this.name = name;
        this.price = price;
    }
}
