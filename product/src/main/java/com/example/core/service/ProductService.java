package com.example.core.service;

import com.example.core.exceptions.InsufficientFundsException;
import com.example.core.model.Product;
import com.example.core.repository.ProductRepository;
import com.example.core.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
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

    @Transactional
    public String makePayment(Long userId, Long productId, BigDecimal amount) {
        var product = productRepository.findById(productId);

        product.orElseThrow(() -> new NoSuchElementException("Не найден продукт " + productId));

        var balance = product.get().getAccountBalance();
        if (balance.compareTo(amount) < 0) {
            throw new InsufficientFundsException("Не достаточно средств");
        }

        product.get().setAccountBalance(balance.subtract(amount));

        productRepository.save(product.get());

        return "Платеж выполнен";
    }
}