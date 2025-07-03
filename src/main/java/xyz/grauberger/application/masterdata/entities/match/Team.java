package xyz.grauberger.application.masterdata.entities.match;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import xyz.grauberger.application.masterdata.entities.player.Player;

@Embeddable
public record Team(@ManyToOne Player firstPlayer, @ManyToOne Player secondPlayer) {

    public Team {
        if (firstPlayer == null || secondPlayer == null) {
            throw new IllegalArgumentException("Both players must be provided");
        }
    }
}
