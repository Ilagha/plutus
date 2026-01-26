package com.company.plutus.api.dto;

import com.company.plutus.domain.TaskStatus;
import java.time.Instant;

public record TaskResponse(
        Long id,
        String title,
        TaskStatus status,
        Instant createdAt
) {}

