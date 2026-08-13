package com.backend.pdunghh.shared.security;

import java.util.UUID;

public final class RequestContext {
    public static final ThreadLocal<UUID> USER_ID = new ThreadLocal<>();
    public static final ThreadLocal<String> REQUEST_ID = new ThreadLocal<>();

    private RequestContext() {
    }

    public static void setUserId(UUID userId) {
        USER_ID.set(userId);
    }

    public UUID getUserId() {
        return USER_ID.get();
    }

    public static void setRequestId(String requestId) {
        REQUEST_ID.set(requestId);
    }

    public static String getRequestId() {
        return REQUEST_ID.get();
    }

    public static void clear() {
        USER_ID.remove();
        REQUEST_ID.remove();
    }
}
