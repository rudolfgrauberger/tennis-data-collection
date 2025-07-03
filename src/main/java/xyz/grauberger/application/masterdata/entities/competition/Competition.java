package xyz.grauberger.application.masterdata.entities.competition;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.grauberger.application.masterdata.entities.Location;
import xyz.grauberger.application.masterdata.entities.Surface;
import xyz.grauberger.application.masterdata.entities.tournament.Tournament;
import xyz.grauberger.application.masterdata.shared.AuditingEntity;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Entity
public class Competition extends AuditingEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    @Embedded
    @AttributeOverride(name = "type", column = @Column(nullable = false, columnDefinition = "TEXT"))
    @AttributeOverride(name = "gender", column = @Column(nullable = false, columnDefinition = "TEXT"))
    private PlayStyle playStyle;

    @ManyToOne
    private Tournament tournament;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "TEXT")
    private Surface surface;

    @NotNull
    @Embedded
    @AttributeOverride(name = "countryISO", column = @Column(nullable = false, columnDefinition = "TEXT"))
    @AttributeOverride(name = "city", column = @Column(nullable = false, columnDefinition = "TEXT"))
    private Location location;

    // Constructor with required fields
    public Competition(String name, Tournament tournament, PlayStyle playStyle, String description) {
        this.name = name;
        this.tournament = tournament;
        this.surface = tournament.getSurface();
        this.location = tournament.getLocation();
        this.playStyle = playStyle;
        this.description = description;
    }

    public Competition(String name, PlayStyle playStyle, Surface surface, Location location, String description) {
        this.name = name;
        this.playStyle = playStyle;
        this.description = description;
        this.surface = surface;
        this.location = location;
    }

    public void update(String name, PlayStyle playStyle, Tournament tournament, String description) {
        this.name = name;
        this.playStyle = playStyle;
        this.tournament = tournament;
        this.surface = tournament.getSurface();
        this.location = tournament.getLocation();
        this.description = description;
    }

    public void update(String name, PlayStyle playStyle, Surface surface, Location location, String description) {
        this.name = name;
        this.playStyle = playStyle;
        this.tournament = null; // No tournament associated
        this.surface = surface;
        this.location = location;
        this.description = description;
    }
}
