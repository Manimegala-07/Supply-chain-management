package com.example.supplychain.service;


import com.example.supplychain.entity.Product;
import com.example.supplychain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product updateProduct(Long id, Product updatedProduct){
        Product product = getProductById(id);
        product.setName(updatedProduct.getName());
        product.setCategory(updatedProduct.getCategory());
        product.setSupplier(updatedProduct.getSupplier());
        product.setPrice(updatedProduct.getPrice());
        product.setStock(updatedProduct.getStock());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}

