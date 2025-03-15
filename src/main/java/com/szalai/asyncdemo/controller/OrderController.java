package com.szalai.asyncdemo.controller;

import com.szalai.asyncdemo.model.dto.AddOrderRequest;
import com.szalai.asyncdemo.model.dto.AddOrderResponse;
import com.szalai.asyncdemo.model.dto.GetOrderResponse;
import com.szalai.asyncdemo.service.OrderProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderProcessor processor;

    @PostMapping
    public ResponseEntity<AddOrderResponse> addOrder(@RequestBody AddOrderRequest request) {
        return ResponseEntity.ok(processor.processAdd(request));
    }

    @GetMapping
    public ResponseEntity<List<GetOrderResponse>> getOrders(@RequestParam (required = false) Boolean dispatched) {
        return ResponseEntity.ok(processor.processGet(dispatched));
    }
}
