package xyz.grauberger.application.masterdata.entities.tournament;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.grauberger.application.masterdata.entities.Location;
import xyz.grauberger.application.masterdata.entities.Surface;
import xyz.grauberger.application.masterdata.shared.AuditingEntity;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Entity
public class Tournament extends AuditingEntity {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "TEXT")
    private Surface surface;

    @NotNull
    @Embedded
    @AttributeOverride(name = "countryISO", column = @Column(nullable = false, columnDefinition = "TEXT"))
    @AttributeOverride(name = "city", column = @Column(nullable = false, columnDefinition = "TEXT"))
    private Location location;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String description;

    public Tournament(@NotNull String name, Surface surface, @NotNull Location location, @NotNull String description) {
        this.name = name;
        this.description = description;
        this.surface = surface;
        this.location = location;
    }

    public void update(@NotNull String name, Surface surface, @NotNull Location location, @NotNull String description) {
        this.name = name;
        this.surface = surface;
        this.location = location;
        this.description = description;
    }
}
