package xyz.grauberger.application.fdi;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import xyz.grauberger.application.fdi.provider.examplecom.repositories.ExampleComCompetitionRepository;
import xyz.grauberger.application.fdi.provider.otherapi.repositories.OtherApiCompetitionRepository;

@Service
@RequiredArgsConstructor
public class DataProviderCompetitionService {

    public record DataProviderCompetition(String id, String name) {}

    private final ExampleComCompetitionRepository exampleComCompetitionRepository;
    private final OtherApiCompetitionRepository otherApiCompetitionRepository;

    public List<DataProviderCompetition> competitions(DataProvider provider) {
        return switch (provider) {
            case OTHER_API -> competitionsFromOtherApi();
            case EXAMPLE_COM -> competitionsFromExampleCom();
            default -> throw new IllegalArgumentException("Unsupported provider: " + provider);
        };
    }

    public Optional<DataProviderCompetition> competitionById(String id, DataProvider provider) {
        return switch (provider) {
            case OTHER_API -> competitionByIdFromOtherApi(id);
            case EXAMPLE_COM -> competitionByIdFromExampleCom(id);
            default -> throw new IllegalArgumentException("Unsupported provider: " + provider);
        };
    }

    private List<DataProviderCompetition> competitionsFromOtherApi() {
        return otherApiCompetitionRepository.findAll().stream()
                .map(comp -> new DataProviderCompetition(comp.getId().toString(), comp.getName()))
                .toList();
    }

    private List<DataProviderCompetition> competitionsFromExampleCom() {
        return exampleComCompetitionRepository.findAll().stream()
                .map(comp -> new DataProviderCompetition(comp.getId(), comp.getName()))
                .toList();
    }

    private Optional<DataProviderCompetition> competitionByIdFromOtherApi(String id) {
        return otherApiCompetitionRepository.findById(UUID.fromString(id))
                .map(comp -> new DataProviderCompetition(comp.getId().toString(), comp.getName()));
    }

    private Optional<DataProviderCompetition> competitionByIdFromExampleCom(String id) {
        return exampleComCompetitionRepository.findById(id)
                .map(comp -> new DataProviderCompetition(comp.getId(), comp.getName()));
    }
}
