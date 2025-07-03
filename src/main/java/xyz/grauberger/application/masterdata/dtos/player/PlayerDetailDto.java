package xyz.grauberger.application.masterdata.dtos.player;

import java.time.LocalDate;

import xyz.grauberger.application.masterdata.entities.player.Gender;

public record PlayerDetailDto(long id, String firstName, String lastName, Gender gender, LocalDate birthDate) {
}
