package xyz.grauberger.application.masterdata.idmapping.provider.examplecom;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import xyz.grauberger.application.fdi.provider.DataProvider;
import xyz.grauberger.application.fdi.provider.examplecom.repositories.ExampleComCompetitionRepository;
import xyz.grauberger.application.fdi.provider.examplecom.repositories.ExampleComMatchRepository;
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

    private final ExampleComCompetitionRepository exampleComCompetitionRepository;
    private final ExampleComMatchRepository exampleComMatchRepository;

    public ExampleComAdapter(ExampleComCompetitionRepository exampleComCompetitionRepository,
            ExampleComMatchRepository exampleComMatchRepository) {
        this.exampleComCompetitionRepository = exampleComCompetitionRepository;
        this.exampleComMatchRepository = exampleComMatchRepository;
    }

    @Override
    public DataProvider provider() {
        return DataProvider.EXAMPLE_COM;
    }

    @Override
    public List<ProviderMatch> matches() {
        return exampleComMatchRepository.findAll().stream()
                .map(match -> new ProviderMatch(match.getId(), match.getName(), match.getMatchDate()))
                .toList();
    }

    @Override
    public Optional<ProviderMatch> matchById(String id) {
        return exampleComMatchRepository.findById(id)
                .map(match -> new ProviderMatch(match.getId(), match.getName(), match.getMatchDate()));
    }

    @Override
    public List<ProviderCompetition> competitions() {
        return exampleComCompetitionRepository.findAll().stream()
                .map(comp -> new ProviderCompetition(comp.getId(), comp.getName()))
                .toList();
    }

    @Override
    public Optional<ProviderCompetition> competitionById(String id) {
        return exampleComCompetitionRepository.findById(id)
                .map(comp -> new ProviderCompetition(comp.getId(), comp.getName()));
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
