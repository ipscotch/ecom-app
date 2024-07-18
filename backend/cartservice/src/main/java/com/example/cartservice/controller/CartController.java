package com.example.cartservice.controller;

import com.example.cartservice.model.Cart;
import com.example.cartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable String id) {
        return ResponseEntity.ok(cartService.getCartById(id));
    }

    @PostMapping("/{id}/items/{itemId}")
    public ResponseEntity<Void> addItemToCart(@PathVariable String id, @PathVariable String itemId, @RequestParam int quantity) {
        cartService.addItemToCart(id, itemId, quantity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/items/{itemId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable String id, @PathVariable String itemId) {
        cartService.removeItemFromCart(id, itemId);
        return ResponseEntity.ok().build();
    }
}
