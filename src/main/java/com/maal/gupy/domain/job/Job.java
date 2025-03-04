package com.maal.gupy.domain.job;

import com.maal.gupy.domain.job.dto.RequestJob;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Table(name="job")
@Entity(name="job")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "identifier")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID identifier;

    private long id;
    private long companyId;
    private String name;
    private String description;
    private long careerPageId;
    private String careerPageName;
    private String careerPageLogo;
    private String type;
    private String publishedDate;
    private String applicationDeadline;
    private boolean isRemoteWork;
    private String city;
    private String state;
    private String country;
    private String jobUrl;
    private boolean disabilities;
    private String workplaceType;
    private String careerPageUrl;

    public Job(RequestJob requestJob) {

        this.id = requestJob.id();
        this.companyId = requestJob.companyId();
        this.name = requestJob.name();
        this.description = requestJob.description();
        this.careerPageId = requestJob.careerPageId();
        this.careerPageName = requestJob.careerPageName();
        this.careerPageLogo = requestJob.careerPageLogo();
        this.type = requestJob.type();
        this.publishedDate = requestJob.publishedDate();
        this.applicationDeadline = requestJob.applicationDeadline();
        this.isRemoteWork = requestJob.isRemoteWork();
        this.city = requestJob.city();
        this.disabilities = requestJob.disabilities();
        this.workplaceType = requestJob.workplaceType();
        this.careerPageUrl = requestJob.careerPageUrl();
    }
}
