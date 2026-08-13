package com.backend.pdunghh.customer.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.pdunghh.customer.dto.response.CustomerResponse;
import com.backend.pdunghh.customer.service.CustomerService;
import com.backend.pdunghh.shared.security.RequestContext;

import lombok.RequiredArgsConstructor;

/**
 * REST Controller quản lý thông tin Khách hàng (Customer)
 */
@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    /**
     * Lấy thông tin Customer của chính mình (IDOR Prevention)
     * <p>
     * Trả về thông tin chi tiết của khách hàng dựa vào JWT Token.
     *
     * @return Dữ liệu hồ sơ khách hàng
     */
    @GetMapping("/me")
    public ResponseEntity<CustomerResponse> getMyProfile() {
        // Luôn dùng RequestContext.getUserId() để đảm bảo Data Isolation theo yêu cầu
        UUID currentUserId = RequestContext.getUserId();
        
        return ResponseEntity.ok(customerService.getMyProfile(currentUserId));
    }
}
