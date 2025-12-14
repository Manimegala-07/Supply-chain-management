package com.example.supplychain.service;


import com.example.supplychain.entity.Order;
import com.example.supplychain.entity.OrderItem;
import com.example.supplychain.entity.Product;
import com.example.supplychain.repository.OrderItemRepository;
import com.example.supplychain.repository.OrderRepository;
import com.example.supplychain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, OrderItemRepository orderItemRepository){
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public Order createOrder(Order order){
        order.setOrderDate(LocalDateTime.now());

        double total = 0;
        for(OrderItem item : order.getItems()){
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if(product.getStock() < item.getQuantity())
                throw new RuntimeException("Insufficient stock for product: " + product.getName());

            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product);

            item.setPrice(product.getPrice() * item.getQuantity());
            item.setOrder(order);
            total += item.getPrice();
        }

        order.setTotalAmount(total);
        Order savedOrder = orderRepository.save(order);
        orderItemRepository.saveAll(savedOrder.getItems());

        return savedOrder;
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id){
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}

