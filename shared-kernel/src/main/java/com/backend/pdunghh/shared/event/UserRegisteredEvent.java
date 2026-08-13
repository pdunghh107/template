package com.backend.pdunghh.shared.event;

import java.util.UUID;

public record UserRegisteredEvent(
        UUID userId, String email) {
}
