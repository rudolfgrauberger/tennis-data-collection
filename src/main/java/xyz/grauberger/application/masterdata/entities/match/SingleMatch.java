package xyz.grauberger.application.masterdata.entities.match;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.grauberger.application.masterdata.entities.competition.Competition;
import xyz.grauberger.application.masterdata.entities.competition.Type;
import xyz.grauberger.application.masterdata.entities.player.Player;
import xyz.grauberger.application.masterdata.services.GenderUtility;
import xyz.grauberger.application.masterdata.services.PlayStyleUtility;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Entity
public class SingleMatch extends Match {
    @NotNull
    @ManyToOne
    private Player firstPlayer;

    @NotNull
    @ManyToOne
    private Player secondPlayer;

    public SingleMatch(@NotNull LocalDate matchDate, @NotNull Competition competition, Player firstPlayer,
            Player secondPlayer) {
        super(matchDate, competition);

        validateInput(competition, firstPlayer, secondPlayer);

        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;

        setMatchName(firstPlayer.getName() + " vs. " + secondPlayer.getName());
    }

    public void update(@NotNull LocalDate matchDate, @NotNull Competition competition, Player firstPlayer, Player secondPlayer) {
        validateInput(competition, firstPlayer, secondPlayer);

        this.matchDate = matchDate;
        this.competition = competition;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;

        setMatchName(firstPlayer.getName() + " vs. " + secondPlayer.getName());
    }

    @Override
    public List<Player> getPlayers() {
        return List.of(firstPlayer, secondPlayer);
    }

    private static void validateInput(Competition competition, Player firstPlayer, Player secondPlayer) {
        if (!PlayStyleUtility.isType(competition, Type.SINGLES)) {
            throw new IllegalArgumentException("Play style of the competition must be SINGLE for a SingleMatch");
        }

        if (!GenderUtility.sameGender(competition, firstPlayer) || !GenderUtility.sameGender(competition, secondPlayer)) {
            throw new IllegalArgumentException("Players must match the competition gender");
        }
    }
}
