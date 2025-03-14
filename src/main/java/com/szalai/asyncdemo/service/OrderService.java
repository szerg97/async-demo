package com.szalai.asyncdemo.service;

import com.szalai.asyncdemo.repository.OrderRepository;
import com.szalai.asyncdemo.model.Order;
import com.szalai.asyncdemo.model.dto.AddOrderRequest;
import com.szalai.asyncdemo.model.dto.AddOrderResponse;
import com.szalai.asyncdemo.model.dto.GetOrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    public AddOrderResponse addOrder(AddOrderRequest request) {
        Order orderSaved = repository.save(request);
        log.info("Order saved: {}, thread: {}", orderSaved.getOrderId(), Thread.currentThread().getName());
        return new AddOrderResponse(orderSaved.getOrderId(), orderSaved.getName(), orderSaved.getPrice());
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
        repository.findAll().stream().filter(order -> order.getOrderId().equals(orderId)).findFirst().ifPresent(order -> order.setDispatched(true));
        log.info("Dispatched order: {}, thread: {}", orderId, Thread.currentThread().getName());
    }

    public List<GetOrderResponse> getOrders() {
        return repository.findAll().stream().map(order -> new GetOrderResponse(
                order.getOrderId(),
                order.getName(),
                order.getPrice(),
                order.getDispatched()
        )).toList();
    }
}
