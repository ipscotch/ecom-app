package com.example.productservice.service;

import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;

import java.util.Optional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Product createProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        rabbitTemplate.convertAndSend("product-exchange", "product.created", savedProduct);
        return savedProduct;
    }

    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }
}
