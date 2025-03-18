package com.example.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record OperationResultDto (
        @JsonProperty(value = "operation-id") UUID uuid,
        @JsonProperty(value = "client-id") Long clientId,
        @JsonProperty(value = "message") String message) {
}
