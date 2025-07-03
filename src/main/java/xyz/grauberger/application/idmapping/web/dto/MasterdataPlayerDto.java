package xyz.grauberger.application.idmapping.web.dto;

import java.time.LocalDate;

public record MasterdataPlayerDto(long id, String name, LocalDate birthDate) {
}
