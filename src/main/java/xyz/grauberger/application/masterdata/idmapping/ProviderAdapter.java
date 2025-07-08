package xyz.grauberger.application.masterdata.idmapping;

import java.util.List;
import java.util.Optional;

import xyz.grauberger.application.fdi.DataProvider;

/**
 * Interface for adapting different data providers to a common format.
 *
 * This allows for uniform access to provider-specific data such as matches, competitions, and players for the ID mapping service.
 * Each provider adapter should implement this interface to provide the necessary data retrieval methods.
 */
public interface ProviderAdapter {
    DataProvider provider();

    List<ProviderMatch> matches();
    Optional<ProviderMatch> matchById(String id);

    List<ProviderCompetition> competitions();
    Optional<ProviderCompetition> competitionById(String id);

    List<ProviderPlayer> players();
    Optional<ProviderPlayer> playerById(String id);
}
