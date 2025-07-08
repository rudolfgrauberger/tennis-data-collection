package xyz.grauberger.application.masterdata.services;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.grauberger.application.fdi.provider.DataProvider;
import xyz.grauberger.application.masterdata.entities.mapping.CompetitionMappingEntity;
import xyz.grauberger.application.masterdata.entities.mapping.CompetitionMappingId;
import xyz.grauberger.application.masterdata.entities.mapping.MatchMappingEntity;
import xyz.grauberger.application.masterdata.entities.mapping.MatchMappingId;
import xyz.grauberger.application.masterdata.entities.mapping.PlayerMappingEntity;
import xyz.grauberger.application.masterdata.entities.mapping.PlayerMappingId;
import xyz.grauberger.application.masterdata.exceptions.CompetitionNotFoundException;
import xyz.grauberger.application.masterdata.exceptions.MatchNotFoundException;
import xyz.grauberger.application.masterdata.exceptions.PlayerNotFoundException;
import xyz.grauberger.application.masterdata.idmapping.Mapping;
import xyz.grauberger.application.masterdata.idmapping.ProviderAdapter;
import xyz.grauberger.application.masterdata.repositories.CompetitionMappingEntityRepository;
import xyz.grauberger.application.masterdata.repositories.CompetitionRepository;
import xyz.grauberger.application.masterdata.repositories.MatchMappingEntityRepository;
import xyz.grauberger.application.masterdata.repositories.MatchRepository;
import xyz.grauberger.application.masterdata.repositories.PlayerMappingEntityRepository;
import xyz.grauberger.application.masterdata.repositories.PlayerRepository;

@Service
public class MapService {
    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;
    private final CompetitionRepository competitionRepository;
    private final CompetitionMappingEntityRepository competitionMappingRepository;
    private final PlayerMappingEntityRepository playerMappingRepositry;
    private final MatchMappingEntityRepository matchMappingRepository;
    private final Map<DataProvider, ProviderAdapter> providers;

    public MapService(PlayerRepository playerRepository, MatchRepository matchRepository,
            CompetitionRepository competitionRepository,
            CompetitionMappingEntityRepository competitionMappingRepository,
            PlayerMappingEntityRepository playerMappingRepositry,
            MatchMappingEntityRepository matchMappingRepository,
            List<ProviderAdapter> providers) {
        this.playerRepository = playerRepository;
        this.matchRepository = matchRepository;
        this.competitionRepository = competitionRepository;
        this.competitionMappingRepository = competitionMappingRepository;
        this.playerMappingRepositry = playerMappingRepositry;
        this.matchMappingRepository = matchMappingRepository;

        this.providers = new EnumMap<>(DataProvider.class);
        for (var provider : providers) {
            this.providers.put(provider.provider(), provider);
        }
    }

    @Transactional
    public Mapping mapCompetition(long id, DataProvider provider, String providerId) {
        final var competition = competitionRepository.findById(id).orElseThrow(() -> new CompetitionNotFoundException(id));
        final var providerAdapter = this.providers.get(provider);
        final var providerCompetition = providerAdapter.competitionById(providerId)
                .orElseThrow(() -> new CompetitionNotFoundException("Competition with ID " + providerId + " not found in provider " + provider));

        final var mappingId = CompetitionMappingId.of(competition, provider);
        final var savedMapping = competitionMappingRepository.save(new CompetitionMappingEntity(mappingId, providerCompetition.id()));

        return new Mapping(savedMapping.getId().competition().getId(), provider, savedMapping.getProviderId());
    }

    @Transactional
    public Mapping mapPlayer(long id, DataProvider provider, String providerId) {
        final var player = playerRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException(id));
        final var providerAdapter = this.providers.get(provider);
        final var providerPlayer = providerAdapter.playerById(providerId)
                .orElseThrow(() -> new PlayerNotFoundException("Player with ID " + providerId + " not found in provider " + provider));

        final var mappingId = PlayerMappingId.of(player, provider);
        final var savedMapping = playerMappingRepositry.save(new PlayerMappingEntity(mappingId, providerPlayer.id()));
        return new Mapping(savedMapping.getId().player().getId(), provider, savedMapping.getProviderId());
    }

    @Transactional
    public Mapping mapMatch(long id, DataProvider provider, String providerId) {
        final var match = matchRepository.findById(id).orElseThrow(() -> new MatchNotFoundException(id));
        final var providerAdapter = this.providers.get(provider);
        final var providerMatch = providerAdapter.matchById(providerId)
                .orElseThrow(() -> new MatchNotFoundException("Match with ID " + providerId + " not found in provider " + provider));

        final var mappingId = MatchMappingId.of(match, provider);
        final var savedMapping = matchMappingRepository.save(new MatchMappingEntity(mappingId, providerMatch.id()));
        return new Mapping(savedMapping.getId().match().getId(), provider, savedMapping.getProviderId());
    }
}
