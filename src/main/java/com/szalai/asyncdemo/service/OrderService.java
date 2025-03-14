package com.szalai.asyncdemo.service;

import com.szalai.asyncdemo.OrderRepository;
import com.szalai.asyncdemo.model.Order;
import com.szalai.asyncdemo.model.dto.OrderRequest;
import com.szalai.asyncdemo.model.dto.OrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    public OrderResponse addOrder(OrderRequest request) {
        Order orderSaved = repository.save(request);
        log.info("Order saved: {}, thread: {}", orderSaved.getOrderId(), Thread.currentThread().getName());
        return new OrderResponse(orderSaved.getOrderId(), orderSaved.getName(), orderSaved.getPrice());
    }

    @Async("asyncTaskExecutor")
    public void notifyCustomer(String orderId) throws InterruptedException {
        log.info("Notifying customer about order: {}, thread: {}", orderId, Thread.currentThread().getName());
        Thread.sleep(2000);
        log.info("Notified customer about order: {}, thread: {}", orderId, Thread.currentThread().getName());
    }

    @Async("asyncTaskExecutor")
    public void dispatchOrder(String orderId) throws InterruptedException {
        log.info("Dispatching order: {}, thread: {}", orderId, Thread.currentThread().getName());
        Thread.sleep(3000);
        log.info("Dispatched order: {}, thread: {}", orderId, Thread.currentThread().getName());
    }
}
