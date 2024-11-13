package com.maal.gupy.domain.job;

import jakarta.persistence.*;
import lombok.*;

@Table(name="badges")
@Entity(name="badges")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "identifier")
public class Badges {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String identifier;

    private boolean friendlyBadge;

}
