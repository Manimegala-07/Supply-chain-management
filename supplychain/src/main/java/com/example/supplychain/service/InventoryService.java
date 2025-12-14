package com.example.supplychain.service;

import com.example.supplychain.entity.Product;
import com.example.supplychain.entity.StockHistory;
import com.example.supplychain.repository.ProductRepository;
import com.example.supplychain.repository.StockHistoryRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class InventoryService {
    private final ProductRepository productRepository;
    private final StockHistoryRepository stockHistoryRepository;

    public InventoryService(ProductRepository productRepository, StockHistoryRepository stockHistoryRepository){
        this.productRepository = productRepository;
        this.stockHistoryRepository = stockHistoryRepository;
    }

    public StockHistory restockProduct(Long productId, int quantity, String remarks){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setStock(product.getStock() + quantity);
        productRepository.save(product);

        StockHistory history = new StockHistory();
        history.setProduct(product);
        history.setQuantity(quantity);
        history.setActionType("ADD");
        history.setRemarks(remarks);
        history.setTimestamp(LocalDateTime.now());
        return stockHistoryRepository.save(history);
    }

    public StockHistory removeStock(Long productId, int quantity, String remarks){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if(product.getStock() < quantity)
            throw new RuntimeException("Insufficient stock");

        product.setStock(product.getStock() - quantity);
        productRepository.save(product);

        StockHistory history = new StockHistory();
        history.setProduct(product);
        history.setQuantity(quantity);
        history.setActionType("REMOVE");
        history.setRemarks(remarks);
        history.setTimestamp(LocalDateTime.now());
        return stockHistoryRepository.save(history);
    }
}

