package com.szalai.asyncdemo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderResponse {
    private String orderId;
    private String name;
    private Double price;
    private Boolean dispatched;
}
