package com.example.payment.configuration.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@Getter
@ConfigurationProperties(prefix = "core-module")
public class RestProperties {

    private final RestClientProps props;

    @ConstructorBinding
    public RestProperties(RestClientProps restClientProps) {

        this.props = restClientProps;
    }
}
