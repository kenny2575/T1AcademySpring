package com.example.core.controller;

import com.example.core.model.Product;
import com.example.core.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class CoreController {

    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        log.info("call GET all products");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getProducts());
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<List<Product>> getProductByClientId(
            @PathVariable("id") Long id
    ) {
        log.info("call GET client products");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getProductsByUserId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(
            @PathVariable("id") Long id
    ) {
        log.info("call GET product by id");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getProductById(id).orElseThrow(RuntimeException::new));
    }

    @PostMapping("/user/{userId}/products/{productId}")
    public ResponseEntity<String> makePayment(
            @PathVariable Long userId,
            @PathVariable Long productId,
            @RequestParam(value = "amount", required = true) BigDecimal amount
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.makePayment(userId, productId, amount));
    }
}
