package xyz.grauberger.application.masterdata.idmapping;

import java.time.LocalDate;

public record ProviderMatch(String id, String name, LocalDate matchDate) implements ProviderIdentity {
}
