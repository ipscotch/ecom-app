package com.example.productservice.service;

import com.example.productservice.model.Product;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ProductEventListener {
    @RabbitListener(queues = "product-events")
    public void handleProductCreated(Product product) {
        // Handle the product created event
        System.out.println("Received product created event: " + product);
    }
}
