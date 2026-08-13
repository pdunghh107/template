package com.backend.pdunghh.auth.dto.response;

import com.backend.pdunghh.auth.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * DTO đại diện cho kết quả trả về sau khi đăng nhập thành công.
 *
 * @param accessToken  Chuỗi JWT dùng để xác thực các API sau này.
 * @param refreshToken Chuỗi Refresh Token (được ẩn khỏi body response bằng
 *                     {@code @JsonIgnore} vì đã lưu trong Cookie).
 * @param user         Thông tin cơ bản của người dùng.
 */
public record LoginResponse(
        String accessToken,
        @JsonIgnore String refreshToken,
        UserResponse user) {
    public static LoginResponse from(String accessToken, String refreshToken, UserEntity user) {
        return new LoginResponse(accessToken, refreshToken, UserResponse.from(user));
    }
}
