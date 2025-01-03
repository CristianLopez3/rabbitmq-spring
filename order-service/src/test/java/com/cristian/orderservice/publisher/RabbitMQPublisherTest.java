package com.cristian.orderservice.publisher;

import com.cristian.orderservice.dto.Order;
import com.cristian.orderservice.dto.OrderEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RabbitMQPublisherTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private RabbitMQPublisher rabbitMQPublisher;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(rabbitMQPublisher, "exchange", "order_exchange");
        ReflectionTestUtils.setField(rabbitMQPublisher, "orderRoutingKey", "order_routing_key");
        ReflectionTestUtils.setField(rabbitMQPublisher, "emailRoutingKey", "email_routing_key");
    }

    @Test
    void testSendOrder() {
        OrderEvent orderEvent = OrderEvent.builder()
                .order(Order.builder().orderName("demo").orderId("123").quantity(3).price(233.23).build())
                .message("Order created successfully")
                .status("CREATED")
                .build();

        rabbitMQPublisher.sendOrder(orderEvent);

        verify(rabbitTemplate).convertAndSend("order_exchange", "order_routing_key", orderEvent);
        verify(rabbitTemplate).convertAndSend("order_exchange", "email_routing_key", orderEvent);
    }
}