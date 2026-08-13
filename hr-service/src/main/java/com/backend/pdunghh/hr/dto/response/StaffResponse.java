package com.backend.pdunghh.hr.dto.response;

import java.util.UUID;

public record StaffResponse(
        UUID id,
        UUID userId,
        String employeeCode,
        String department,
        UUID branchId,
        String title
) {
}
