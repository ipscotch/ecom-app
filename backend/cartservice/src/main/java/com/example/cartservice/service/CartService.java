package com.example.cartservice.service;

import com.example.cartservice.model.Cart;
import com.example.cartservice.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart getCartByUsername(String username) {
        return cartRepository.findByUsername(username).orElse(new Cart(username));
    }

    public void addItemToCart(String username, String itemId, int quantity) {
        Cart cart = getCartByUsername(username);
        cart.getItems().put(itemId, cart.getItems().getOrDefault(itemId, 0) + quantity);
        cartRepository.save(cart);
    }

    public void removeItemFromCart(String username, String itemId) {
        Cart cart = getCartByUsername(username);
        cart.getItems().remove(itemId);
        cartRepository.save(cart);
    }
}

