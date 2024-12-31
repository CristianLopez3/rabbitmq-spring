package com.cristian.rabbitmqtutorial.publisher;

import com.cristian.rabbitmqtutorial.dto.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQJsonProducer {

    @Value(("${rabbitmq.routing.json.key}"))
    private String routingJsonKey;

    @Value(("${rabbitmq.exchange.name}"))
    private String exchange;

    private final RabbitTemplate rabbitTemplate;
    private final Logger logger = LoggerFactory.getLogger(RabbitMQJsonProducer.class);

    public void sendMessage(User message) {
        logger.info("JSON Message send: {}", message);
        rabbitTemplate.convertAndSend(
                exchange,
                routingJsonKey,
                message
        );
    }


}

