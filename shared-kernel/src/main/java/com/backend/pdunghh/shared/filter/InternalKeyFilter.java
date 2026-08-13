package com.backend.pdunghh.shared.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.backend.pdunghh.shared.auth.InternalServiceAuthUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(1) // Chạy sớm nhất
public class InternalKeyFilter extends OncePerRequestFilter {

    @Value("${app.internal-service-key:dev-internal-key}")
    private String configuredKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String path = request.getRequestURI();
        
        // Chỉ áp dụng filter này cho các API nội bộ
        if (path.startsWith("/api/internal/")) {
            String providedKey = request.getHeader(InternalServiceAuthUtils.INTERNAL_SERVICE_KEY_HEADER);
            
            try {
                InternalServiceAuthUtils.validateServiceKey(configuredKey, providedKey);
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write("{\"code\": 403, \"message\": \"Từ chối truy cập hệ thống nội bộ\"}");
                return; // Chặn luồng
            }
        }
        
        // Cho qua nếu hợp lệ hoặc không phải API nội bộ
        filterChain.doFilter(request, response);
    }
}
