package com.cristian.emailservice.consumer;

import com.cristian.emailservice.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {

    private final Logger logger = LoggerFactory.getLogger(EmailConsumer.class);

    @RabbitListener(queues = "${rabbitmq.queue.email.name}")
    public void consume(OrderEvent orderEvent) {
        logger.info("Consuming order to send an email from RabbitMQ: {}", orderEvent);
        // send an email to the user.
    }

}
