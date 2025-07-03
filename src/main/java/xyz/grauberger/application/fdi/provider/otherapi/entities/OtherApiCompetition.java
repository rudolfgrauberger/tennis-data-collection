package xyz.grauberger.application.fdi.provider.otherapi.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(schema = "fdi", name = "otherapi_competition")
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
public class OtherApiCompetition {
    @Id
    private UUID id;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String name;
}
