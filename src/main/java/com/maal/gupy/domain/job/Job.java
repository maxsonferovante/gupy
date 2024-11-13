package com.maal.gupy.domain.job;

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
    private String identifier;

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
    @ManyToOne
    @JoinColumn(name = "badges_identifier")
    private Badges badges;
    private boolean disabilities;
    private String workplaceType;
    private String careerPageUrl;


}
