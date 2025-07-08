package xyz.grauberger.application.masterdata.dtos.mapping;

import java.time.LocalDate;

public record MasterdataMatchDto(long id, String name, LocalDate matchDate) {
}
