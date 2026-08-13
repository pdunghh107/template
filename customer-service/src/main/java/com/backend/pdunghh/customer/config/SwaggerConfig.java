package com.backend.pdunghh.customer.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Customer Service API", version = "v1", description = "Tài liệu API cho Customer Service. Hỗ trợ tự động đọc Javadoc."), security = @SecurityRequirement(name = "bearerAuth"))
public class SwaggerConfig {
}
