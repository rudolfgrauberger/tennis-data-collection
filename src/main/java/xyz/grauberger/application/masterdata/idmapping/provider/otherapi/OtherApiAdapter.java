package xyz.grauberger.application.masterdata.idmapping.provider.otherapi;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import xyz.grauberger.application.fdi.provider.DataProvider;
import xyz.grauberger.application.fdi.provider.otherapi.repositories.OtherApiCompetitionRepository;
import xyz.grauberger.application.fdi.provider.otherapi.repositories.OtherApiMatchRepository;
import xyz.grauberger.application.fdi.provider.otherapi.repositories.OtherApiPlayerRepository;
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

    private final OtherApiCompetitionRepository otherApiCompetitionRepository;
    private final OtherApiMatchRepository otherApiMatchRepository;
    private final OtherApiPlayerRepository otherApiPlayerRepository;

    public OtherApiAdapter(OtherApiCompetitionRepository otherApiCompetitionRepository,
            OtherApiMatchRepository otherApiMatchRepository,
            OtherApiPlayerRepository otherApiPlayerRepository) {
        this.otherApiCompetitionRepository = otherApiCompetitionRepository;
        this.otherApiMatchRepository = otherApiMatchRepository;
        this.otherApiPlayerRepository = otherApiPlayerRepository;
    }

    @Override
    public DataProvider provider() {
        return DataProvider.OTHER_API;
    }

    @Override
    public List<ProviderMatch> matches() {
        return otherApiMatchRepository.findAll().stream()
                .map(match -> new ProviderMatch(match.getId().toString(), match.getName(), match.getMatchDate()))
                .toList();
    }

    @Override
    public Optional<ProviderMatch> matchById(String id) {
        final var providerId = UUID.fromString(id);
        return otherApiMatchRepository.findById(providerId)
                .map(match -> new ProviderMatch(match.getId().toString(), match.getName(), match.getMatchDate()));
    }

    @Override
    public List<ProviderCompetition> competitions() {
        return otherApiCompetitionRepository.findAll().stream()
                .map(comp -> new ProviderCompetition(comp.getId().toString(), comp.getName()))
                .toList();
    }

    @Override
    public Optional<ProviderCompetition> competitionById(String id) {
        final var providerId = UUID.fromString(id);
        return otherApiCompetitionRepository.findById(providerId)
                .map(comp -> new ProviderCompetition(comp.getId().toString(), comp.getName()));
    }

    @Override
    public List<ProviderPlayer> players() {
        return otherApiPlayerRepository.findAll().stream()
                .map(player -> new ProviderPlayer(player.getId().toString(), player.getName(), player.getBirthDate()))
                .toList();
    }

    @Override
    public Optional<ProviderPlayer> playerById(String id) {
        final var providerId = UUID.fromString(id);
        return otherApiPlayerRepository.findById(providerId)
                .map(player -> new ProviderPlayer(player.getId().toString(), player.getName(), player.getBirthDate()));
    }
}
