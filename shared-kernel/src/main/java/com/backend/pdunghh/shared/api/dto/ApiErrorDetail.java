package com.backend.pdunghh.shared.api.dto;

public record ApiErrorDetail(
        String code,
        String field,
        String message) {
}
