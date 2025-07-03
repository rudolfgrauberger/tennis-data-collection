package xyz.grauberger.application.masterdata.dtos.tournament;


import jakarta.validation.constraints.NotNull;
import xyz.grauberger.application.masterdata.entities.Surface;

public record TournamentInputDto(
    @NotNull String name,
    @NotNull Surface surface,
    @NotNull String city,
    @NotNull String isoCountry,
    @NotNull String description) {
}
