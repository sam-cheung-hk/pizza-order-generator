package com.challenge.order.generator.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customizedOpenAPI() {
        Info info = new Info()
                .title("Pizza Order Generator API")
                .description("Coding Challenge - Service for Generating Pizza Order");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }

}
