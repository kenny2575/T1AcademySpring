package com.example.payment.service;

import com.example.payment.configuration.properties.LimitRestProperties;
import com.example.payment.dto.OperationResultDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class LimitService {

    private final RestClient client;
    private final LimitRestProperties limitRestProperties;

    public LimitService(@Qualifier("limitRest") RestClient client, LimitRestProperties limitRestProperties) {
        this.client = client;
        this.limitRestProperties = limitRestProperties;
    }


    public OperationResultDto confirm(UUID operation) {
        String path = limitRestProperties.getProps().getServices().getOrDefault("confirm", null);

        return client.post()
                .uri(uriBuilder -> uriBuilder.path(path)
                        .queryParam("operation_id", operation)
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    public OperationResultDto reject(UUID operation) {
        String path = limitRestProperties.getProps().getServices().getOrDefault("reject", null);
        return client.post()
                .uri(uriBuilder -> uriBuilder.path(path)
                        .queryParam("operation_id", operation)
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    public UUID setHold(Long userId, BigDecimal amount) {
        String path = limitRestProperties.getProps().getServices().getOrDefault("hold", null);

        OperationResultDto resultDto = client.post()
                .uri(uriBuilder -> uriBuilder.path(path)
                        .queryParam("amount", amount)
                        .queryParam("client_id", userId)
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
        return resultDto.uuid();
    }
}
