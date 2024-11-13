package com.maal.gupy.domain.job;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
