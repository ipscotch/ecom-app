package com.example.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class PaymentRequest {
    private String currency;
    private long amount;
    private String source;
    private String description;

    
}
