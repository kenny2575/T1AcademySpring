package com.example.payment.service;

import com.example.payment.configuration.properties.CoreRestProperties;
import com.example.payment.dto.OperationResultDto;
import com.example.payment.dto.ProductDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final RestClient client;
    private final CoreRestProperties coreRestProperties;
    private final LimitService limitService;

    public ProductService(@Qualifier("coreRest") RestClient client, CoreRestProperties coreRestProperties, LimitService limitService) {
        this.client = client;
        this.coreRestProperties = coreRestProperties;
        this.limitService = limitService;
    }

    public List<ProductDto> getProductByUserId(Long userId) {
        String path = coreRestProperties.getProps().getServices().getOrDefault("get-product-by-client", null);
        return client.get()
                .uri(uriBuilder -> uriBuilder.path(path)
                        .build(userId))
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
    public OperationResultDto makePayment(Long userId, Long productId, BigDecimal amount) {
        String path = coreRestProperties.getProps().getServices().getOrDefault("post-payment", null);
        String result;
        UUID operation = limitService.setHold(userId, amount);
        try {
            result = client.post()
                    .uri(uriBuilder -> uriBuilder.path(path)
                            .queryParam("amount", amount)
                            .build(userId, productId))
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            limitService.confirm(operation);
        } catch (Exception e) {
            result = "Ошибка обработки";
            limitService.reject(operation);
        }
        return new OperationResultDto(operation, userId, result);
    }
}
