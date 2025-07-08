package xyz.grauberger.application.masterdata.idmapping;

import xyz.grauberger.application.fdi.DataProvider;

public record Mapping(long ourId, DataProvider provider, String providerId) {
}
