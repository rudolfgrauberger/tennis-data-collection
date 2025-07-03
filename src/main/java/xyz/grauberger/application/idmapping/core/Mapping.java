package xyz.grauberger.application.idmapping.core;

import xyz.grauberger.application.fdi.provider.DataProvider;

public record Mapping(long ourId, DataProvider provider, String providerId) {
}
