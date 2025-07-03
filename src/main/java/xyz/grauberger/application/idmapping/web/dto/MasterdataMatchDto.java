package xyz.grauberger.application.idmapping.web.dto;

import java.time.LocalDate;

public record MasterdataMatchDto(long id, String name, LocalDate matchDate) {
}
