package com.example.cartservice.repository;

import com.example.cartservice.model.Cart;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CartRepository {
     private static final String CART_KEY_PREFIX = "cart:";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public Optional<Cart> findByUsername(String username) {
        Cart cart = (Cart) redisTemplate.opsForValue().get(CART_KEY_PREFIX + username);
        return Optional.ofNullable(cart);
    }

    public void save(Cart cart) {
        redisTemplate.opsForValue().set(CART_KEY_PREFIX + cart.getUsername(), cart);
    }

    public void delete(String username) {
        redisTemplate.delete(CART_KEY_PREFIX + username);
    }
}
