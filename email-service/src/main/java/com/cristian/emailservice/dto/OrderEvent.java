package com.cristian.emailservice.dto;

import lombok.Builder;

@Builder
public record OrderEvent(
    String status, // PENDING, PROCESSED
    String message,
    Order order
) { }
