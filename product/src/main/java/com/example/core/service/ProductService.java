package com.example.core.service;

import com.example.core.model.Product;
import com.example.core.repository.ProductRepository;
import com.example.core.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductsByUserId(Long userId) {
        return productRepository.findByUserId(userId);
    }

    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }
}