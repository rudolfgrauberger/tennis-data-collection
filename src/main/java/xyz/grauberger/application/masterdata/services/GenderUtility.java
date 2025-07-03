package xyz.grauberger.application.masterdata.services;

import xyz.grauberger.application.masterdata.entities.competition.Competition;
import xyz.grauberger.application.masterdata.entities.player.Player;

public class GenderUtility {

    private GenderUtility() {
        // Private constructor to prevent instantiation
    }

    public static boolean sameGender(Competition competition, Player player) {
        final var competitionGender = competition.getPlayStyle().gender();
        final var playerGender = player.getGender();

        return xyz.grauberger.application.masterdata.entities.competition.Gender.MEN.equals(competitionGender) && xyz.grauberger.application.masterdata.entities.player.Gender.MEN.equals(playerGender) ||
               xyz.grauberger.application.masterdata.entities.competition.Gender.WOMEN.equals(competitionGender) && xyz.grauberger.application.masterdata.entities.player.Gender.WOMEN.equals(playerGender);
    }
}
