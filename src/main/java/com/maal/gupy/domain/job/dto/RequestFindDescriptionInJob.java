package com.maal.gupy.domain.job.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestFindDescriptionInJob(
        @NotNull
        @NotBlank
        String description
) {
}
