package com.cristian.rabbitmqtutorial.publisher;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQProducer {

    @Value(("${rabbitmq.exchange.name}"))
    private String exchange;

    @Value(("${rabbitmq.routing.key}"))
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;
    private final Logger logger = LoggerFactory.getLogger(RabbitMQProducer.class);

    public void sendMessage(String message) {
        logger.info("Message send: {}", message);
        rabbitTemplate.convertAndSend(
                exchange,
                routingKey,
                message
        );
    }

}
