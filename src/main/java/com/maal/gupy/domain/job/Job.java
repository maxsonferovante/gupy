package com.maal.gupy.domain.job;

import jakarta.persistence.*;
import lombok.*;

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
