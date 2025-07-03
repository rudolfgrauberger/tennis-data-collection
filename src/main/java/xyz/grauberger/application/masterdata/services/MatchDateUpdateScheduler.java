package xyz.grauberger.application.masterdata.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import xyz.grauberger.application.fdi.FdiMatchService;
import xyz.grauberger.application.fdi.provider.DataProvider;
import xyz.grauberger.application.idmapping.service.IdMappingMatchService;
import xyz.grauberger.application.masterdata.repositories.MatchRepository;

@Component
@RequiredArgsConstructor
public class MatchDateUpdateScheduler {
    private final MatchRepository matchRepository;
    private final IdMappingMatchService idMappingMatchService;
    private final FdiMatchService fdiMatchService;

    @Scheduled(cron = "0 0 0 * * ?") // Runs daily at midnight
    public void updateMatchDates() {
        final var matches = matchRepository.findAll();

        final var mainDataProvider = DataProvider.EXAMPLE_COM;

        for (var match : matches) {
            final var providerId = idMappingMatchService.getProviderId(match.getId(), mainDataProvider);
            final var latestMatchDate = fdiMatchService.getLatestMatchDate(providerId, mainDataProvider);

            if (latestMatchDate != null && !latestMatchDate.equals(match.getMatchDate())) {
                match.rescheduleMatch(latestMatchDate);
                matchRepository.save(match);
            }
        }
    }
}
