package com.jgibank.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition
public class SwaggerConfiguration {
    
    @Bean
    public OpenAPI config() {
        return new OpenAPI().info(
                new Info()
                        .title("JGIBANK API")
                        .version("v1")
                        .description("API for JGIBanking application")
        );
    }
}