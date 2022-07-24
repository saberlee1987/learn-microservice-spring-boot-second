package com.saber.multiplication_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Value(value = "${service.openapi.description}")
    private String openApiDescription;
    @Value(value = "${service.openapi.version}")
    private String openApiVersion;
    @Value(value = "${service.openapi.title}")
    private String openApiTitle;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .description(openApiDescription)
                        .version(openApiVersion)
                        .title(openApiTitle));
    }
}
