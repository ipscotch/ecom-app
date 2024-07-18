package com.example.paymentservice.service;

import com.example.paymentservice.config.StripeConfig;
import com.example.paymentservice.model.PaymentCreatedEvent;
import com.example.paymentservice.model.PaymentRequest;
import com.example.paymentservice.model.PaymentResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class PaymentService {

    private final StripeConfig stripeConfig;
    private final EventPublisher eventPublisher;

    @Autowired
    public PaymentService(StripeConfig stripeConfig, EventPublisher eventPublisher) {
        this.stripeConfig = stripeConfig;
        this.eventPublisher = eventPublisher;
    }

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeConfig.getApiKey();
    }

    public PaymentResponse charge(PaymentRequest paymentRequest) throws StripeException {
        ChargeCreateParams params = ChargeCreateParams.builder()
                .setAmount(paymentRequest.getAmount())
                .setCurrency(paymentRequest.getCurrency())
                .setDescription(paymentRequest.getDescription())
                .setSource(paymentRequest.getSource())
                .build();

        Charge charge = Charge.create(params);

        PaymentCreatedEvent event = new PaymentCreatedEvent();
        event.setAmount(paymentRequest.getAmount());
        event.setCurrency(paymentRequest.getCurrency());
        event.setDescription(paymentRequest.getDescription());
        event.setSource(paymentRequest.getSource());

        eventPublisher.publishPaymentEvent(event);

        return new PaymentResponse(charge.getStatus(), charge.getId(), charge.getBalanceTransaction());
    }
}
