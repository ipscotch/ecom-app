package com.example.cartservice.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("Cart")
public class Cart implements Serializable {
    private String id;
    private Map<String, Integer> items = new HashMap<>();

    public Cart() {
    }

    public Cart(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public void setItems(Map<String, Integer> items) {
        this.items = items;
    }
}
