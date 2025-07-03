package xyz.grauberger.application.masterdata.services;

import jakarta.validation.constraints.NotNull;
import xyz.grauberger.application.masterdata.entities.competition.Competition;
import xyz.grauberger.application.masterdata.entities.competition.Gender;
import xyz.grauberger.application.masterdata.entities.competition.Type;
import xyz.grauberger.application.masterdata.entities.match.Team;

public class PlayStyleUtility {

    private PlayStyleUtility() {
        // Private constructor to prevent instantiation
    }

    public static boolean isType(@NotNull Competition competition, Type exptedType) {
        final var playStyle = competition.getPlayStyle();
        return playStyle.type() == exptedType;
    }

    public static boolean isValidPlayStyleForTeams(@NotNull Competition competition, Team firstTeam, Team secondTeam) {
        final var competitionGender = competition.getPlayStyle().gender();

        if (competitionGender.equals(Gender.MIXED)) {
            // Each team must consist of one male player and one female player
            return firstTeam.firstPlayer().getGender() != firstTeam.secondPlayer().getGender()
                    && secondTeam.firstPlayer().getGender() != secondTeam.secondPlayer().getGender();
        }

        // If not mixed, then both teams must have the same gender
        return firstTeam.firstPlayer().getGender() == firstTeam.secondPlayer().getGender()
                && secondTeam.firstPlayer().getGender() == secondTeam.secondPlayer().getGender()
                && firstTeam.firstPlayer().getGender() == secondTeam.firstPlayer().getGender();
    }
}
