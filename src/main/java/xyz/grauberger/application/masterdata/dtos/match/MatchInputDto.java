package xyz.grauberger.application.masterdata.dtos.match;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;


public record MatchInputDto(
    @NotNull LocalDate matchDate,
    long competitionId,
    long firstTeamFirstPlayerId,
    Long firstTeamSecondPlayerId,
    long secondTeamFirstPlayerId,
    Long secondTeamSecondPlayerId
) {
    public enum MatchType {
        SINGLE,
        DOUBLE
    }

    public MatchInputDto {
        if (firstTeamFirstPlayerId == secondTeamFirstPlayerId) {
            throw new IllegalArgumentException("Player IDs must be different");
        }
        if (firstTeamSecondPlayerId != null && firstTeamFirstPlayerId == firstTeamSecondPlayerId) {
            throw new IllegalArgumentException("First team player IDs must be different");
        }
        if (secondTeamSecondPlayerId != null && secondTeamFirstPlayerId == secondTeamSecondPlayerId) {
            throw new IllegalArgumentException("Second team player IDs must be different");
        }
    }

    @JsonIgnore
    public MatchType getMatchType() {
        return isDoubleMatch() ? MatchType.DOUBLE : MatchType.SINGLE;
    }

    @JsonIgnore
    public boolean isDoubleMatch() {
        return firstTeamSecondPlayerId != null && secondTeamSecondPlayerId != null;
    }
}
