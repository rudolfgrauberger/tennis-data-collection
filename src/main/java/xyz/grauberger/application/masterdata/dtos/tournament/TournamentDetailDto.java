package xyz.grauberger.application.masterdata.dtos.tournament;

import xyz.grauberger.application.masterdata.entities.Surface;

public record TournamentDetailDto(
    long id,
    String name,
    Surface surface,
    String city,
    String isoCountry,
    String description
) {
}
