package com.company.plutus.api.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateTaskRequest(
        @NotBlank(message = "title is required")
        String title
) {}

