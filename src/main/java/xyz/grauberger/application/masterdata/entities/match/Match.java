package xyz.grauberger.application.masterdata.entities.match;

import java.time.LocalDate;
import java.util.List;

import org.springframework.lang.NonNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.grauberger.application.masterdata.entities.competition.Competition;
import xyz.grauberger.application.masterdata.entities.player.Player;
import xyz.grauberger.application.masterdata.shared.AuditingEntity;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Match extends AuditingEntity {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    protected LocalDate matchDate;

    @NotNull
    @ManyToOne
    protected Competition competition;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String name;

    Match(@NotNull LocalDate matchDate, @NotNull Competition competition) {
        this.matchDate = matchDate;
        this.competition = competition;
    }

    protected void setMatchName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Match name cannot be null or blank");
        }
        this.name = name;
    }

    public List<Player> getPlayers() {
        // This method should be overridden in subclasses to return the list of players
        throw new UnsupportedOperationException("This method should be overridden in subclasses");
    }

    public void rescheduleMatch(@NonNull LocalDate newDate) {
        this.matchDate = newDate;
    }
}
