package xyz.grauberger.application.idmapping.core;

import java.time.LocalDate;

public record ProviderPlayer(String id, String name, LocalDate birthDate) implements ProviderIdentity {
}
