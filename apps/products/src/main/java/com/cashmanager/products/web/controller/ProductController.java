package com.cashmanager.products.web.controller;

import com.cashmanager.products.exception.ResourceNotFoundException;
import com.cashmanager.products.model.Product;
import com.cashmanager.products.repository.ProductRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class ProductController {
    @Autowired
    private ProductRepository ProductRepository;

    @GetMapping(value="/Products")
    public Page<Product>getProducts(Pageable pageable) {
        return ProductRepository.findAll(pageable);
    }

    @GetMapping(value="/Products/{productId}")
    public Product getProductById(@PathVariable long productId) {
        return ProductRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + productId));
    }

    @PostMapping("/Products")
    public Product createProduct(@Valid @RequestBody Product product) {
        return ProductRepository.save(product);
    }

    @PutMapping("/Products/{productId}")
    public Product updateProduct(@PathVariable long productId,
                                   @Valid @RequestBody Product productRequest) {
        return ProductRepository.findById(productId)
                .map(product -> {
                    product.setPrice(productRequest.getPrice());
                    product.setName(productRequest.getName());
                    product.setImgUrl(productRequest.getImgUrl());
                    product.setQuantity(productRequest.getQuantity());
                    product.setDescription(productRequest.getDescription());
                    return ProductRepository.save(product);
                }).orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + productId));
    }

    @DeleteMapping("/Products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable long productId) {
        return ProductRepository.findById(productId)
                .map(product -> {
                    ProductRepository.delete(product);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + productId));
    }
}