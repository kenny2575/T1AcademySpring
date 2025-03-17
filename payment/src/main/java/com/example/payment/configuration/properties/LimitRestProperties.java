package com.example.payment.configuration.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@Getter
@ConfigurationProperties(prefix = "limit-module")
public class LimitRestProperties {
    private final RestClientProps props;

    @ConstructorBinding
    public LimitRestProperties(RestClientProps restClientProps) {
        this.props = restClientProps;
    }
}
