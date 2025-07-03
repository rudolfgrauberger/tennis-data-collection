package xyz.grauberger.application.masterdata.dtos.competition;

import xyz.grauberger.application.masterdata.entities.Surface;
import xyz.grauberger.application.masterdata.entities.competition.Gender;
import xyz.grauberger.application.masterdata.entities.competition.Type;

public record CompetitionDetailDto(
    long id,
    String name,
    Type type,
    Gender gender,
    Surface surface,
    String city,
    String isoCountry,
    String description
) {
}
