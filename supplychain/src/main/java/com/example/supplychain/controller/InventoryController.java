package com.example.supplychain.controller;


import com.example.supplychain.entity.StockHistory;
import com.example.supplychain.service.InventoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService){
        this.inventoryService = inventoryService;
    }

    @PostMapping("/restock")
    public StockHistory restock(@RequestParam Long productId, @RequestParam int quantity, @RequestParam String remarks){
        return inventoryService.restockProduct(productId, quantity, remarks);
    }

    @PostMapping("/remove")
    public StockHistory remove(@RequestParam Long productId, @RequestParam int quantity, @RequestParam String remarks){
        return inventoryService.removeStock(productId, quantity, remarks);
    }
}

