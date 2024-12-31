package com.cristian.rabbitmqtutorial.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value(("${rabbitmq.queue.name}"))
    private String queue;

    @Value(("${rabbitmq.exchange.name}"))
    private String exchange;

    @Value(("${rabbitmq.routing.key}"))
    private String routingKey;

    /**
     * Spring AMQP provides a Queue class that represents a queue in RabbitMQ.
     * @return
     */
    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    /**
     * Spring AMQP provides a TopicExchange class that represents an exchange in RabbitMQ.
     * @return
     */
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    /**
     * Spring AMQP provides a Binding class that represents a binding in RabbitMQ.
     * Bind the actual Queue to the actual Exchange with the routing key.
     * @return
     */
    @Bean
    public Binding binding(){
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routingKey);

    }

    /**
     * Spring AMQP provides a RabbitTemplate class that provides methods to send messages to RabbitMQ.
     * ConnectionFactory
     * RabbitTemplate
     * Rabbit Bean
     */


}
