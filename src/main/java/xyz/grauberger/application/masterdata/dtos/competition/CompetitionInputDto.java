package xyz.grauberger.application.masterdata.dtos.competition;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import xyz.grauberger.application.masterdata.entities.Surface;
import xyz.grauberger.application.masterdata.entities.competition.Gender;
import xyz.grauberger.application.masterdata.entities.competition.Type;

public record CompetitionInputDto(
    @NotNull String name,
    String description,
    @NotNull Type type,
    @NotNull Gender gender,
    @Schema(title = "ID of the tournament this competition belongs to",
    description = """
        Note: If this field is set, the city, country, and surface fields will be ignored,
        as they will be extracted from the tournament.
        If this field is not set, the city, country, and surface fields must be provided.
    """)
    Long tournamentId,
    @Schema(title = "City where the competition takes place.",
    description = """
        Note: This field is ignored if tournamentId is set, as the city will be extracted from the tournament.
    """)
    String city,
    @Schema(title = "ISO country code where the competition takes place.",
    description = """
        Note: This field is ignored if tournamentId is set, as the country will be extracted from the tournament.
    """)
    String isoCountry,
    @Schema(title = "Surface on which the competition is played.",
    description = """
        Note: This field is ignored if tournamentId is set, as the surface will be extracted from the tournament.
    """)
    Surface surface
) {
}
