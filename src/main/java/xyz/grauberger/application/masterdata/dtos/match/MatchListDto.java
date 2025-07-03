package xyz.grauberger.application.masterdata.dtos.match;

import java.time.LocalDate;

public record MatchListDto(
    long id,
    LocalDate matchDate,
    String competitionName,
    String type,
    String name
) {
}
