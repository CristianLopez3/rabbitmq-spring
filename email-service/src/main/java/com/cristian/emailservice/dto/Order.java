package com.cristian.emailservice.dto;

import lombok.Builder;

@Builder
public record Order(
        String orderId,
        String orderName,
        int quantity,
        double price
) { }
