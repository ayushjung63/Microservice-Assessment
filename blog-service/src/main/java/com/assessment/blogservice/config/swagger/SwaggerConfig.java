package com.assessment.blogservice.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

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
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        paramName = "Authorization",
        description = "Bearer token"
)
@Configuration
public class SwaggerConfig {
}
