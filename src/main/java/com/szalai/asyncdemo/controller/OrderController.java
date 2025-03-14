package com.szalai.asyncdemo.controller;

import com.szalai.asyncdemo.model.dto.AddOrderRequest;
import com.szalai.asyncdemo.model.dto.AddOrderResponse;
import com.szalai.asyncdemo.model.dto.GetOrderResponse;
import com.szalai.asyncdemo.service.OrderProcessor;
import com.szalai.asyncdemo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderProcessor processor;
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<AddOrderResponse> placeOrder(@RequestBody AddOrderRequest request) {
        return ResponseEntity.ok(processor.process(request));
    }

    @GetMapping
    public ResponseEntity<List<GetOrderResponse>> getOrders() {
        return ResponseEntity.ok(orderService.getOrders());
    }
}
