package com.movies_service.movies_service_demo.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.client.RestTemplateBuilderConfigurer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class RestTemplateBuilderConfig {

    @Value("${maverik.client.base.url}")
    private String MAVERIK_BASE_URL;

    @Bean
    RestTemplateBuilder restTemplateBuilder(RestTemplateBuilderConfigurer configurer){

        assert MAVERIK_BASE_URL != null;

        return configurer.configure(new RestTemplateBuilder())
                .uriTemplateHandler(new DefaultUriBuilderFactory(MAVERIK_BASE_URL));
    }
}
