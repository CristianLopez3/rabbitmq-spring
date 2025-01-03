package com.cristian.orderservice.dto;

import lombok.Builder;

@Builder
public record Order(
        String orderId,
        String orderName,
        int quantity,
        double price
) { }
