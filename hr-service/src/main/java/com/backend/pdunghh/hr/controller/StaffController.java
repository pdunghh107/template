package com.backend.pdunghh.hr.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.pdunghh.hr.dto.response.StaffResponse;
import com.backend.pdunghh.hr.service.StaffService;
import com.backend.pdunghh.shared.security.RequestContext;

import lombok.RequiredArgsConstructor;

/**
 * REST Controller quản lý thông tin Nhân sự (Staff)
 */
@RestController
@RequestMapping("/api/v1/staffs")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    /**
     * Lấy thông tin Staff của chính mình (IDOR Prevention)
     * <p>
     * Trả về thông tin chi tiết của nhân sự dựa vào JWT Token.
     *
     * @return Dữ liệu hồ sơ nhân sự
     */
    @GetMapping("/me")
    public ResponseEntity<StaffResponse> getMyProfile() {
        // Luôn dùng RequestContext.getUserId() để đảm bảo Data Isolation theo yêu cầu
        UUID currentUserId = RequestContext.getUserId();

        return ResponseEntity.ok(staffService.getMyProfile(currentUserId));
    }
}
