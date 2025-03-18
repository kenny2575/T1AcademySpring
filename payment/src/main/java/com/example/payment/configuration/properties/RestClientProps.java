package com.example.payment.configuration.properties;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class RestClientProps {
    private String url;
    private Long timeout;
    private Map<String, String> services;
}
