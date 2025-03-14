package com.szalai.asyncdemo.service;

import com.szalai.asyncdemo.model.dto.AddOrderRequest;
import com.szalai.asyncdemo.model.dto.AddOrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderProcessor {

    private final OrderService orderService;

    public AddOrderResponse process(AddOrderRequest request) {
        final AddOrderResponse response = orderService.addOrder(request);
        try {
            orderService.notifyCustomer(response.getOrderId());
            orderService.dispatchOrder(response.getOrderId());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
