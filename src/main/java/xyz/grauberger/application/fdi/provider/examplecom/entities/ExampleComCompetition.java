package xyz.grauberger.application.fdi.provider.examplecom.entities;

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
@Table(schema = "fdi", name = "examplecom_competition")
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
public class ExampleComCompetition {
    @Id
    @Column(columnDefinition = "TEXT")
    String id;

    @NotNull
    @Column(columnDefinition = "TEXT")
    String name;

    @NotNull
    @Column(columnDefinition = "TEXT")
    String city;
}
