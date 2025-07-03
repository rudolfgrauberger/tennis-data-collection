package xyz.grauberger.application.fdi.provider.examplecom.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(schema = "fdi", name = "examplecom_match")
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
public class ExampleComMatch {
    @Id
    @Column(columnDefinition = "TEXT")
    private String id;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String name;

    private LocalDate matchDate;

    @NotNull
    @ManyToOne
    private ExampleComCompetition competition;
}
