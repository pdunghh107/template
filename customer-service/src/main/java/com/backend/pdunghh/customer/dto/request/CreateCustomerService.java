package com.backend.pdunghh.customer.dto.request;

import java.util.UUID;

public record CreateCustomerService(
        UUID userId,
        String cifNo,
        String kycStatus,
        String address,
        String tier) {
}
