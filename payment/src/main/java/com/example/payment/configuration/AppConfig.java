package com.example.payment.configuration;

import com.example.payment.configuration.properties.CoreRestProperties;
import com.example.payment.configuration.properties.LimitRestProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class AppConfig {

    @Bean(name = "coreRest")
    public RestClient coreRestClient(CoreRestProperties props) {
        return RestClient.builder()
                .baseUrl(props.getProps().getUrl())
                .build();
    }

    @Bean(name = "limitRest")
    public RestClient limitRestClient(LimitRestProperties props) {
        return RestClient.builder()
                .baseUrl(props.getProps().getUrl())
                .build();
    }
}
