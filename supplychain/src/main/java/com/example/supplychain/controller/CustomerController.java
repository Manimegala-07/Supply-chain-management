package com.example.supplychain.controller;


import com.example.supplychain.entity.Customer;
import com.example.supplychain.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAll(){
        return customerService.getAllCustomers();
    }

    @PostMapping
    public Customer add(@RequestBody Customer customer){
        return customerService.addCustomer(customer);
    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }
}
