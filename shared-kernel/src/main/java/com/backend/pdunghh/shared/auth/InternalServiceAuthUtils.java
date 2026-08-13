package com.backend.pdunghh.shared.auth;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import com.backend.pdunghh.shared.exception.BusinessException;

public final class InternalServiceAuthUtils {

    public static final String INTERNAL_SERVICE_KEY_HEADER = "X-Internal-Key";

    private InternalServiceAuthUtils() {
    }

    public static void validateServiceKey(String configuredKey, String providedKey) {
        if (!StringUtils.hasText(configuredKey) || !configuredKey.equals(providedKey)) {
            // Using standard HttpStatus.FORBIDDEN value (403)
            throw new BusinessException(HttpStatus.FORBIDDEN, "Từ chối truy cập hệ thống nội bộ");
        }
    }
}
