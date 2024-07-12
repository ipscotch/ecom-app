package com.example.userservice.service;

import com.example.userservice.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class UserEventListener {
    @RabbitListener(queues = "user-events")
    public void handleUserCreated(User user) {
        // Handle the user created event
        System.out.println("Received user created event: " + user);
    }
}

