package com.backend.pdunghh.auth.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Auth Service API", version = "v1", description = "Tài liệu API cho Auth Service thuộc dự án Testify. Hỗ trợ tự động đọc Javadoc để hiển thị Swagger."), security = @SecurityRequirement(name = "bearerAuth"))
public class SwaggerConfig {
}
