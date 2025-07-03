package xyz.grauberger.application.masterdata.services;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import xyz.grauberger.application.fdi.provider.DataProvider;
import xyz.grauberger.application.idmapping.service.IdMappingMatchService;

@Service
@RequiredArgsConstructor
public class MatchVideoService {
    private final IdMappingMatchService idMappingMatchService;

    public String getVideoUrl(long matchId) {
        final var videoProviderId = idMappingMatchService.getProviderId(matchId, DataProvider.EXAMPLE_COM);

        if (videoProviderId == null) {
            return null; // No video available for this match
        }

        // Placeholder for actual video URL retrieval logic
        return "https://example.com/video/" + videoProviderId;
    }
}
