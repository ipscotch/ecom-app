package com.example.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PaymentCreatedEvent {
    private String currency;
    private long amount;
    private String source;
    private String description;

     
}

