package com.socialsphere.auth.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record Response(
        UUID id,
        String username,
        String email,
        String message,
        LocalDateTime createdAt
) {
}
