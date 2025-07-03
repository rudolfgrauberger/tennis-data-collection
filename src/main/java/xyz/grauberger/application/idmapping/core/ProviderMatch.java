package xyz.grauberger.application.idmapping.core;

import java.time.LocalDate;

public record ProviderMatch(String id, String name, LocalDate matchDate) implements ProviderIdentity {
}
