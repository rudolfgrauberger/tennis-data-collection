package xyz.grauberger.application.masterdata.services;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.grauberger.application.fdi.DataProvider;
import xyz.grauberger.application.masterdata.dtos.mapping.MasterdataCompetitionDto;
import xyz.grauberger.application.masterdata.dtos.mapping.MasterdataMatchDto;
import xyz.grauberger.application.masterdata.dtos.mapping.MasterdataPlayerDto;
import xyz.grauberger.application.masterdata.idmapping.ProviderAdapter;
import xyz.grauberger.application.masterdata.idmapping.ProviderCompetition;
import xyz.grauberger.application.masterdata.idmapping.ProviderMatch;
import xyz.grauberger.application.masterdata.idmapping.ProviderPlayer;
import xyz.grauberger.application.masterdata.repositories.CompetitionRepository;
import xyz.grauberger.application.masterdata.repositories.MatchRepository;
import xyz.grauberger.application.masterdata.repositories.PlayerRepository;

@Service
@Transactional(readOnly = true)
public class MappingDataService {
    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;
    private final CompetitionRepository competitionRepository;
    private final Map<DataProvider, ProviderAdapter> providers;

    public MappingDataService(PlayerRepository playerRepository, MatchRepository matchRepository,
            CompetitionRepository competitionRepository, List<ProviderAdapter> providers) {
        this.playerRepository = playerRepository;
        this.matchRepository = matchRepository;
        this.competitionRepository = competitionRepository;

        this.providers = new EnumMap<>(DataProvider.class);
        for (var provider : providers) {
            this.providers.put(provider.provider(), provider);
        }
    }

    public List<MasterdataMatchDto> getMatches() {
        return matchRepository.findAll().stream()
                .map(match -> new MasterdataMatchDto(match.getId(), match.getName(), match.getMatchDate()))
                .toList();
    }

    public List<ProviderMatch> getMatchesByProvider(DataProvider provider) {
        var adapter = providers.get(provider);
        return adapter.matches().stream()
                .toList();
    }

    public List<MasterdataPlayerDto> getPlayers() {
        return playerRepository.findAll().stream()
                .map(player -> new MasterdataPlayerDto(player.getId(), player.getName(), player.getDateOfBirth()))
                .toList();
    }

    public List<ProviderPlayer> getPlayersByProvider(DataProvider provider) {
        var adapter = providers.get(provider);
        return adapter.players().stream()
                .toList();
    }

    public List<MasterdataCompetitionDto> getCompetitions() {
        return competitionRepository.findAll().stream()
                .map(comp -> new MasterdataCompetitionDto(comp.getId(), comp.getName()))
                .toList();
    }

    public List<ProviderCompetition> getCompetitionsByProvider(DataProvider provider) {
        var adapter = providers.get(provider);
        return adapter.competitions().stream()
                .map(comp -> new ProviderCompetition(comp.id(), comp.name()))
                .toList();
    }
}
