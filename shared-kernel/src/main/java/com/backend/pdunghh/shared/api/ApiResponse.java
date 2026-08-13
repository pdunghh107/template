package com.backend.pdunghh.shared.api;

import com.backend.pdunghh.shared.api.dto.PageResponse;
import com.backend.pdunghh.shared.api.enums.ApiSuccessCode;
import com.backend.pdunghh.shared.security.RequestContext;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(
        boolean success,
        String code,
        String message,
        T data, String requestId) {

    private static <T> ApiResponse<T> buildResponse(boolean success, ApiSuccessCode code, String message, T data) {
        String finalMessage = (message == null || message.trim().isEmpty()) ? "Thành công" : message;
        return new ApiResponse<>(success, code.value(), finalMessage, data, RequestContext.getRequestId());
    }

    public static <T> ApiResponse<T> ok(T data) {
        return buildResponse(true, ApiSuccessCode.OK, null, data);
    }

    public static <T> ApiResponse<T> ok(T data, String message) {
        return buildResponse(true, ApiSuccessCode.OK, message, data);
    }

    public static <T> ApiResponse<T> created(T data) {
        return buildResponse(true, ApiSuccessCode.CREATED, null, data);
    }

    public static <T> ApiResponse<T> message(String message) {
        return buildResponse(true, ApiSuccessCode.MESSAGE, message, null);
    }

    public static <T> ApiResponse<PageResponse<T>> paged(PageResponse<T> page) {
        return buildResponse(true, ApiSuccessCode.PAGED, null, page);
    }
}
