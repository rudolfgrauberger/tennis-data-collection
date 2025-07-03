package xyz.grauberger.application.idmapping.core;

/**
 * Interface representing an identity for a provider.
 *
 * This is used to uniquely identify entities across different providers.
 */
public interface ProviderIdentity {
    String id();
}
