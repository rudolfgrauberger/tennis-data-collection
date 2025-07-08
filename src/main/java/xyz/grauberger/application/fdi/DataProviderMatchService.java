package xyz.grauberger.application.fdi;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import xyz.grauberger.application.fdi.provider.examplecom.repositories.ExampleComMatchRepository;
import xyz.grauberger.application.fdi.provider.otherapi.repositories.OtherApiMatchRepository;

@Service
@RequiredArgsConstructor
public class DataProviderMatchService {
    private final OtherApiMatchRepository otherApiMatchRepository;
    private final ExampleComMatchRepository exampleComMatchRepository;

    public record DataProviderMatch(String id, String name, LocalDate matchDate) {}


    public List<DataProviderMatch> matches(DataProvider provider) {
        return switch (provider) {
            case OTHER_API -> matchesFromOtherApi();
            case EXAMPLE_COM -> matchesFromExampleCom();
            default -> throw new IllegalArgumentException("Unsupported provider: " + provider);
        };
    }

    public Optional<DataProviderMatch> matchById(String id, DataProvider provider) {
        return switch (provider) {
            case OTHER_API -> matchByIdFromOtherApi(id);
            case EXAMPLE_COM -> matchByIdFromExampleCom(id);
            default -> throw new IllegalArgumentException("Unsupported provider: " + provider);
        };
    }

    private Optional<DataProviderMatch> matchByIdFromOtherApi(String id) {
        return otherApiMatchRepository.findById(UUID.fromString(id))
                .map(match -> new DataProviderMatch(match.getId().toString(), match.getName(), match.getMatchDate()));
    }

    private Optional<DataProviderMatch> matchByIdFromExampleCom(String id) {
        return exampleComMatchRepository.findById(id)
                .map(match -> new DataProviderMatch(match.getId(), match.getName(), match.getMatchDate()));
    }

    private List<DataProviderMatch> matchesFromOtherApi() {
        return otherApiMatchRepository.findAll().stream()
                .map(match -> new DataProviderMatch(match.getId().toString(), match.getName(), match.getMatchDate()))
                .toList();
    }

    private List<DataProviderMatch> matchesFromExampleCom() {
        return exampleComMatchRepository.findAll().stream()
                .map(match -> new DataProviderMatch(match.getId(), match.getName(), match.getMatchDate()))
                .toList();
    }
}
