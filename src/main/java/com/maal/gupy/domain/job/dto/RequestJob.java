package com.maal.gupy.domain.job.dto;


public record RequestJob(
        long id,
        long companyId,
        String name,

        String description,
    long careerPageId,
    String careerPageName,
    String careerPageLogo,
    String type,
    String publishedDate,
    String applicationDeadline,
    boolean isRemoteWork,
    String city,
    String state,
    String country,
    String jobUrl,
    boolean disabilities,
    String workplaceType,
    String careerPageUrl
) {
}
