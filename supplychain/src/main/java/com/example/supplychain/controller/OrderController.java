package com.example.supplychain.controller;


import com.example.supplychain.entity.Order;
import com.example.supplychain.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getAll(){
        return orderService.getAllOrders();
    }

    @PostMapping
    public Order create(@RequestBody Order order){
        return orderService.createOrder(order);
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable Long id){
        return orderService.getOrderById(id);
    }
}

