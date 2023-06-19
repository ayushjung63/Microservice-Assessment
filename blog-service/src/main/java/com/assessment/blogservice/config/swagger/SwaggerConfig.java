package com.assessment.blogservice.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
* This is the config file for Swagger.
* */
@OpenAPIDefinition(
info = @Info(
        title = "Blog Service API",
        version = "v1",
        description = "This is the documentation of Blog Service APIs"
),
        servers = {
        @Server(
                url="http://localhost:8070/blog",
                description = "Local Server"
        )
        }
)
@Configuration
public class SwaggerConfig {

        @Bean
        public OpenAPI customizeOpenAPI() {
                final String securitySchemeName = "bearerAuth";
                return new OpenAPI()
                        .addSecurityItem(new SecurityRequirement()
                                .addList(securitySchemeName))
                        .components(new Components()
                                .addSecuritySchemes(securitySchemeName, new io.swagger.v3.oas.models.security.SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
                                        .scheme("Bearer")
                                        .bearerFormat("JWT")));
        }

}
