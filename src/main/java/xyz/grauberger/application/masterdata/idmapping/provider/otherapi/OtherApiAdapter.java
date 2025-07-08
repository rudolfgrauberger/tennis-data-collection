package xyz.grauberger.application.masterdata.idmapping.provider.otherapi;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import xyz.grauberger.application.fdi.DataProvider;
import xyz.grauberger.application.fdi.DataProviderCompetitionService;
import xyz.grauberger.application.fdi.DataProviderMatchService;
import xyz.grauberger.application.fdi.DataProviderPlayerService;
import xyz.grauberger.application.masterdata.idmapping.ProviderAdapter;
import xyz.grauberger.application.masterdata.idmapping.ProviderCompetition;
import xyz.grauberger.application.masterdata.idmapping.ProviderMatch;
import xyz.grauberger.application.masterdata.idmapping.ProviderPlayer;

/**
 * Adapter for the OtherApi data provider.
 *
 * This service implements the ProviderAdapter interface to provide access to matches, competitions, and players
 * from the OtherApi provider.
 */
@Service
public class OtherApiAdapter implements ProviderAdapter{

    private final DataProviderCompetitionService competitionService;
    private final DataProviderMatchService matchService;
    private final DataProviderPlayerService playerService;

    private static final DataProvider provider = DataProvider.OTHER_API;

    public OtherApiAdapter(DataProviderCompetitionService competitionService,
            DataProviderMatchService matchService,
            DataProviderPlayerService playerService) {
        this.competitionService = competitionService;
        this.matchService = matchService;
        this.playerService = playerService;
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
        return playerService.players(provider).stream()
                .map(player -> new ProviderPlayer(player.id(), player.name(), player.birthDate()))
                .toList();
    }

    @Override
    public Optional<ProviderPlayer> playerById(String id) {
        return playerService.playerById(id, provider)
                .map(player -> new ProviderPlayer(player.id(), player.name(), player.birthDate()));
    }
}
