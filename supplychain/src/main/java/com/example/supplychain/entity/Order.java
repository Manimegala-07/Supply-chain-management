package com.example.supplychain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="orders")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    private LocalDateTime orderDate;
    private double totalAmount;

    @OneToMany(mappedBy="order", cascade=CascadeType.ALL)
    @JsonManagedReference
    private List<OrderItem> items;
}
