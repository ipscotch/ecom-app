package com.example.paymentservice.service;

import com.example.paymentservice.model.PaymentCreatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publishPaymentEvent(PaymentCreatedEvent event) {
        rabbitTemplate.convertAndSend("paymentQueue", event);
    }
}
