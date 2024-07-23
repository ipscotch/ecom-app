package com.example.orderservice;

import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.service.OrderService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrder() {
        Order order = new Order();
        order.setProduct("Test Product");
        order.setQuantity(1);
        order.setPrice(100.0);
        order.setUsername("testuser");

        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order createdOrder = orderService.createOrder(order);

        assertNotNull(createdOrder);
        assertEquals("Test Product", createdOrder.getProduct());
        assertEquals(1, createdOrder.getQuantity());
        assertEquals(100.0, createdOrder.getPrice());
        assertEquals("testuser", createdOrder.getUsername());

        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testGetAllOrders() {
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        order.setProduct("Test Product");
        order.setQuantity(1);
        order.setPrice(100.0);
        order.setUsername("testuser");
        orders.add(order);

        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> retrievedOrders = orderService.getAllOrders();

        assertNotNull(retrievedOrders);
        assertEquals(1, retrievedOrders.size());
        assertEquals("Test Product", retrievedOrders.get(0).getProduct());

        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void testGetOrdersByUsername() {
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        order.setProduct("Test Product");
        order.setQuantity(1);
        order.setPrice(100.0);
        order.setUsername("testuser");
        orders.add(order);

        when(orderRepository.findByUsername("testuser")).thenReturn(orders);

        List<Order> retrievedOrders = orderService.getOrdersByUsername("testuser");

        assertNotNull(retrievedOrders);
        assertEquals(1, retrievedOrders.size());
        assertEquals("Test Product", retrievedOrders.get(0).getProduct());

        verify(orderRepository, times(1)).findByUsername("testuser");
    }

    @Test
    void testGetOrderById() {
        Order order = new Order();
        order.setProduct("Test Product");
        order.setQuantity(1);
        order.setPrice(100.0);
        order.setUsername("testuser");

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Order retrievedOrder = orderService.getOrderById(1L);

        assertNotNull(retrievedOrder);
        assertEquals("Test Product", retrievedOrder.getProduct());
        assertEquals(1, retrievedOrder.getQuantity());
        assertEquals(100.0, retrievedOrder.getPrice());

        verify(orderRepository, times(1)).findById(1L);
    }
}
