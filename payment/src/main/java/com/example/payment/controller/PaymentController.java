package com.example.payment.controller;


import com.example.payment.dto.OperationResultDto;
import com.example.payment.dto.ProductDto;
import com.example.payment.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final ProductService productService;

    @GetMapping("/user/{id}/products")
    public ResponseEntity<List<ProductDto>> getProducts(@PathVariable("id") Long userId) {
        log.info("call GET products for client {}", userId);
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductByUserId(userId));
    }

    @PostMapping("/user/{userId}/products/{productId}")
    public ResponseEntity<OperationResultDto> makePayment(
            @PathVariable Long userId,
            @PathVariable Long productId,
            @RequestParam(value = "amount") BigDecimal amount
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.makePayment(userId, productId, amount));
    }

}
