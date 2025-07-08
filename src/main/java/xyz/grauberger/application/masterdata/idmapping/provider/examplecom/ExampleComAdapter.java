package xyz.grauberger.application.masterdata.idmapping.provider.examplecom;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import xyz.grauberger.application.fdi.DataProvider;
import xyz.grauberger.application.fdi.DataProviderCompetitionService;
import xyz.grauberger.application.fdi.DataProviderMatchService;
import xyz.grauberger.application.masterdata.idmapping.ProviderAdapter;
import xyz.grauberger.application.masterdata.idmapping.ProviderCompetition;
import xyz.grauberger.application.masterdata.idmapping.ProviderMatch;
import xyz.grauberger.application.masterdata.idmapping.ProviderPlayer;

/**
 * Adapter for the ExampleCom data provider.
 *
 * This service implements the ProviderAdapter interface to provide access to matches, competitions, and players
 * from the ExampleCom provider.
 */
@Service
public class ExampleComAdapter implements ProviderAdapter {

    private final DataProviderCompetitionService competitionService;
    private final DataProviderMatchService matchService;

    private static final DataProvider provider = DataProvider.EXAMPLE_COM;

    public ExampleComAdapter(DataProviderCompetitionService competitionService,
            DataProviderMatchService matchService) {
        this.competitionService = competitionService;
        this.matchService = matchService;
    }

    @Override
    public DataProvider provider() {
        return provider;
    }

    @Override
    public List<ProviderMatch> matches() {
        return matchService.matches(provider).stream()
                .map(match -> new ProviderMatch(match.id(), match.name(), match.matchDate()))
                .toList();
    }

    @Override
    public Optional<ProviderMatch> matchById(String id) {
        return matchService.matchById(id, provider)
                .map(match -> new ProviderMatch(match.id(), match.name(), match.matchDate()));
    }

    @Override
    public List<ProviderCompetition> competitions() {
        return competitionService.competitions(provider).stream()
                .map(comp -> new ProviderCompetition(comp.id(), comp.name()))
                .toList();
    }

    @Override
    public Optional<ProviderCompetition> competitionById(String id) {
        return competitionService.competitionById(id, provider)
                .map(comp -> new ProviderCompetition(comp.id(), comp.name()));
    }

    @Override
    public List<ProviderPlayer> players() {
        return List.of(); // ExampleCom does not provide player data
    }

    @Override
    public Optional<ProviderPlayer> playerById(String id) {
        return Optional.empty(); // ExampleCom does not provide player data
    }
}
