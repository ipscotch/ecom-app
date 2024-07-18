package com.example.cartservice.service;

import com.example.cartservice.model.Cart;
import com.example.cartservice.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart getCartById(String id) {
        return cartRepository.findById(id).orElse(new Cart(id));
    }

    public void addItemToCart(String cartId, String itemId, int quantity) {
        Cart cart = getCartById(cartId);
        cart.getItems().put(itemId, cart.getItems().getOrDefault(itemId, 0) + quantity);
        cartRepository.save(cart);
    }

    public void removeItemFromCart(String cartId, String itemId) {
        Cart cart = getCartById(cartId);
        cart.getItems().remove(itemId);
        cartRepository.save(cart);
    }
}

