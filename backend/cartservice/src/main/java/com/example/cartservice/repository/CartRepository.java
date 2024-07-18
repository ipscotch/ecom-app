package com.example.cartservice.repository;

import com.example.cartservice.model.Cart;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends KeyValueRepository<Cart, String>  {
}
