package com.example.paymentservice.service;

import com.example.paymentservice.model.PaymentCreatedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentEventListener {

    @RabbitListener(queues = "paymentQueue")
    public void handlePaymentEvent(PaymentCreatedEvent event) {
        // Handle the payment event, e.g., log it or update another service
        System.out.println("Received payment event: " + event);
    }
}