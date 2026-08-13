package com.backend.pdunghh.shared.feign;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.backend.pdunghh.shared.auth.InternalServiceAuthUtils;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
public class InternalKeyFeignInterceptor implements RequestInterceptor {

    @Value("${app.internal-service-key:dev-internal-key}")
    private String configuredKey;

    @Override
    public void apply(RequestTemplate template) {
        // Tự động đính kèm header X-Internal-Key vào mọi request Feign
        template.header(InternalServiceAuthUtils.INTERNAL_SERVICE_KEY_HEADER, configuredKey);
    }
}
