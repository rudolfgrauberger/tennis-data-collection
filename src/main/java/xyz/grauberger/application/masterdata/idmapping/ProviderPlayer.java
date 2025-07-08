package xyz.grauberger.application.masterdata.idmapping;

import java.time.LocalDate;

public record ProviderPlayer(String id, String name, LocalDate birthDate) implements ProviderIdentity {
}
