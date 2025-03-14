package com.szalai.asyncdemo.controller;

import com.szalai.asyncdemo.model.dto.OrderRequest;
import com.szalai.asyncdemo.model.dto.OrderResponse;
import com.szalai.asyncdemo.service.OrderProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderProcessor processor;

    @PostMapping
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest request) {
        return ResponseEntity.ok(processor.process(request));
    }
}
