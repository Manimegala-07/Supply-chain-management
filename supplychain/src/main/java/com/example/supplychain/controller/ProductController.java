package com.example.supplychain.controller;

import com.example.supplychain.entity.Product;
import com.example.supplychain.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAll(){
        return productService.getAllProducts();
    }

    @PostMapping
    public Product add(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product){
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}


