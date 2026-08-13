package com.backend.pdunghh.auth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.auth")
public record AuthProperties(
                String jwtSecret,
                String jwtIssuer,
                int accessTokenMinutes,
                int refreshTokenDays) {

}
