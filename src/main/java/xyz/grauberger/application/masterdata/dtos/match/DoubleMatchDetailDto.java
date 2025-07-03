package xyz.grauberger.application.masterdata.dtos.match;

import java.time.LocalDate;

public record DoubleMatchDetailDto(long id, LocalDate matchDate, long competitionId, Team firstTeam, Team secondTeam) implements MatchDetailDto {
    public record Team(long firstPlayerId, long secondPlayerId) {
    }
}
