package xyz.grauberger.application.collection.tool.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import xyz.grauberger.application.collection.tool.domain.KPI;
import xyz.grauberger.application.masterdata.entities.match.Match;
import xyz.grauberger.application.masterdata.entities.player.Player;

@Getter
@Entity
public class PlayerKpi {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(columnDefinition = "TEXT")
    @Enumerated(EnumType.STRING)
    private KPI.KpiName name;

    @NotNull
    @ManyToOne
    private Player player;

    @NotNull
    @ManyToOne
    private Match match;

    @NotNull
    private Long value;

    protected PlayerKpi() {
        // Default constructor for JPA
    }

    public PlayerKpi(KPI kpi, Player player, Match match) {
        this.name = kpi.name();
        this.player = player;
        this.match = match;
        this.value = kpi.value();
    }
}
