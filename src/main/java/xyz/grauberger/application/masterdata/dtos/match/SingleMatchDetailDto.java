package xyz.grauberger.application.masterdata.dtos.match;

import java.time.LocalDate;

public record SingleMatchDetailDto(long id, LocalDate matchDate, long competitionId, long firstPlayerId, long secondPlayerId) implements MatchDetailDto {
}
