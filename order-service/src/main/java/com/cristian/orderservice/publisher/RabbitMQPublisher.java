package com.cristian.orderservice.publisher;

import com.cristian.orderservice.dto.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQPublisher {

    private final Logger logger = LoggerFactory.getLogger(RabbitMQPublisher.class);

    @Value("${rabbitmq.exchange.order.name}")
    private String exchange;

    @Value("${rabbitmq.routing.order.name}")
    private String orderRoutingKey;

    @Value("${rabbitmq.routing.email.name}")
    private String emailRoutingKey;

    private final RabbitTemplate rabbitTemplate;

    public void sendOrder(OrderEvent orderEvent) {
        logger.info("Sending order to RabbitMQ: {}", orderEvent);
        rabbitTemplate.convertAndSend(exchange, orderRoutingKey, orderEvent);
        rabbitTemplate.convertAndSend(exchange, emailRoutingKey, orderEvent);
    }

}
