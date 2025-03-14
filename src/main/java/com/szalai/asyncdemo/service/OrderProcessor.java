package com.szalai.asyncdemo.service;

import com.szalai.asyncdemo.model.dto.OrderRequest;
import com.szalai.asyncdemo.model.dto.OrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderProcessor {

    private final OrderService orderService;

    public OrderResponse process(OrderRequest request) {
        final OrderResponse response = orderService.addOrder(request);
        try {
            orderService.notifyCustomer(response.getOrderId());
            orderService.dispatchOrder(response.getOrderId());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
