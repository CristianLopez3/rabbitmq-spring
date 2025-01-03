package com.cristian.orderservice.controller;

import com.cristian.orderservice.dto.Order;
import com.cristian.orderservice.dto.OrderEvent;
import com.cristian.orderservice.publisher.RabbitMQPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OrderController {

    private final RabbitMQPublisher publisher;

    @PostMapping("orders")
    public ResponseEntity<String> publishOrder(@RequestBody Order order) {
        var orderEvent = OrderEvent.builder()
                .status("PENDING")
                .message("Order received, preparing...")
                .order(order)
                .build();

        publisher.sendOrder(orderEvent);
        return ResponseEntity.ok("Order sent to RabbitMQ");
    }


}
