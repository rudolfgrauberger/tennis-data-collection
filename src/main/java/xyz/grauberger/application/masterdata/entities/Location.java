package xyz.grauberger.application.masterdata.entities;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

@Embeddable
public record Location(@NotNull String countryISO, @NotNull String city) {
}
