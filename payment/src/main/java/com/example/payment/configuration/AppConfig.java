package com.example.payment.configuration;

import com.example.payment.configuration.properties.RestProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClient;

@Configuration
public class AppConfig {

    @Bean
    public RestClient coreRestClient(RestProperties props) {
        return RestClient.builder()
                .baseUrl(props.getProps().getUrl())
                .build();
    }
}
