package com.maal.gupy.domain.job.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestJob(

        @NotBlank
        @NotNull
        long id,
        @NotBlank
        @NotNull
        long companyId,
        @NotBlank
        @NotNull
        String name,
        @NotBlank
        @NotNull
        String description,
    long careerPageId,
    String careerPageName,
    String careerPageLogo,
    String type,
    String publishedDate,
    String applicationDeadline,
        @NotBlank
        @NotNull
        boolean isRemoteWork,
    String city,
    String state,
    String country,
    String jobUrl,
        @NotBlank
        @NotNull
        RequestBadge badges,
    boolean disabilities,
    String workplaceType,
    String careerPageUrl
) {
}
