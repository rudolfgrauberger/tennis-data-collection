package xyz.grauberger.application.masterdata.dtos.player;

import java.time.LocalDate;

import xyz.grauberger.application.masterdata.entities.player.Gender;

public record PlayerListDto (long id, String name, Gender gender, LocalDate birthDate) {
}
