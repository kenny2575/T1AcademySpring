package com.example.payment.service;

import com.example.payment.configuration.properties.RestProperties;
import com.example.payment.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final RestClient client;
    private final RestProperties properties;
    public List<ProductDto> getProductByUserId(Long userId) {
        String path = properties.getProps().getServices().getOrDefault("get-product-by-client", null);
        return client.get()
                .uri(uriBuilder -> uriBuilder.path(path)
                        .build(userId))
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
    public String makePayment(Long userId, Long productId, BigDecimal amount) {
        String path = properties.getProps().getServices().getOrDefault("post-payment", null);
        return client.post()
                .uri(uriBuilder -> uriBuilder.path(path)
                        .queryParam("amount", amount)
                        .build(userId, productId))
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}
