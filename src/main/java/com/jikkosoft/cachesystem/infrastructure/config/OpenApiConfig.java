package com.jikkosoft.cachesystem.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI cacheSystemOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Jikkosoft Cache Service")
                .version("1.0.0")
                .description("Distributed cache REST API backed by Redis and built with Hexagonal Architecture.")
                .contact(new Contact().name("Jikkosoft").url("https://github.com/raziel214/cachesystem"))
                .license(new License().name("MIT").url("https://opensource.org/licenses/MIT")));
    }
}
