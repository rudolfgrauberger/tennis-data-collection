package xyz.grauberger.application.masterdata.dtos.mapping;

import java.time.LocalDate;

public record MasterdataPlayerDto(long id, String name, LocalDate birthDate) {
}
