package com.backend.pdunghh.customer.dto.response;

import java.util.UUID;

public record CustomerResponse(
        UUID id,
        UUID userId,
        String cifNo,
        String kycStatus,
        String address,
        String tier
) {
}
