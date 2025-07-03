package xyz.grauberger.application.masterdata.dtos.player;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import xyz.grauberger.application.masterdata.entities.player.Gender;

public record PlayerInputDto(
    @NotNull String firstName,
    @NotNull String lastName,
    @NotNull Gender gender,
    @NotNull LocalDate birthDate) {
}
