package com.example.resilience.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import javax.validation.constraints.NotNull;

public record RequestWrapper<T, U>(
    @Schema(
            description = "Request ID",
            example = "12345",
            requiredMode = Schema.RequiredMode.REQUIRED,
            format = "uuid")
        @NotNull(message = "Request ID cannot be null")
        String requestId,
    @Schema(description = "Request payload", requiredMode = Schema.RequiredMode.REQUIRED)
        @Valid
        @NotNull(message = "Request payload cannot be null")
        T request,
    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED) U responseView) {}
