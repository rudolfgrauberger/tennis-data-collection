package xyz.grauberger.application.masterdata.entities.match;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.grauberger.application.masterdata.entities.competition.Competition;
import xyz.grauberger.application.masterdata.entities.competition.Type;
import xyz.grauberger.application.masterdata.entities.player.Player;
import xyz.grauberger.application.masterdata.services.PlayStyleUtility;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Entity
public class DoubleMatch extends Match {

    @Embedded
    @AssociationOverride(name = "firstPlayer", joinColumns = @JoinColumn(name = "first_team_first_player_id", nullable = false))
    @AssociationOverride(name = "secondPlayer", joinColumns = @JoinColumn(name = "first_team_second_player_id", nullable = false))
    private Team firstTeam;

    @Embedded
    @AssociationOverride(name = "firstPlayer", joinColumns = @JoinColumn(name = "second_team_first_player_id", nullable = false))
    @AssociationOverride(name = "secondPlayer", joinColumns = @JoinColumn(name = "second_team_second_player_id", nullable = false))
    private Team secondTeam;

    public DoubleMatch(@NotNull LocalDate matchDate, @NotNull Competition competition, @NotNull Team firstTeam, @NotNull Team secondTeam) {
        super(matchDate, competition);

        validateInput(competition, firstTeam, secondTeam);

        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;

        setMatchName(firstTeam.firstPlayer().getName() + " & " + firstTeam.secondPlayer().getName() + " vs. "
                + secondTeam.firstPlayer().getName() + " & " + secondTeam.secondPlayer().getName());
    }

    public void update(@NotNull LocalDate matchDate, @NotNull Competition competition, @NotNull Team firstTeam, @NotNull Team secondTeam) {
        validateInput(competition, firstTeam, secondTeam);
        this.matchDate = matchDate;
        this.competition = competition;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;


        setMatchName(firstTeam.firstPlayer().getName() + " & " + firstTeam.secondPlayer().getName() + " vs. "
                + secondTeam.firstPlayer().getName() + " & " + secondTeam.secondPlayer().getName());
    }

    @Override
    public List<Player> getPlayers() {
        return List.of(firstTeam.firstPlayer(), firstTeam.secondPlayer(), secondTeam.firstPlayer(), secondTeam.secondPlayer());
    }

    private static void validateInput(Competition competition, Team firstTeam, Team secondTeam) {
        if (!PlayStyleUtility.isType(competition, Type.DOUBLES)) {
            throw new IllegalArgumentException("Play style of the competition must be DOUBLES for a DoubleMatch");
        }

        if (!PlayStyleUtility.isValidPlayStyleForTeams(competition, firstTeam, secondTeam)) {
            throw new IllegalArgumentException("Teams do not match the competition play style requirements");
        }
    }
}
