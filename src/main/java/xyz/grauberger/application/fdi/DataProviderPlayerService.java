package xyz.grauberger.application.fdi;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import xyz.grauberger.application.fdi.provider.otherapi.repositories.OtherApiPlayerRepository;

@Service
@RequiredArgsConstructor
public class DataProviderPlayerService {
    public record DataProviderPlayer(String id, String name, LocalDate birthDate) {}

    private final OtherApiPlayerRepository otherApiPlayerRepository;

    public List<DataProviderPlayer> players(DataProvider provider) {
        return switch (provider) {
            case OTHER_API -> playersFromOtherApi();
            case EXAMPLE_COM -> throw new UnsupportedOperationException("ExampleCom provider not supported for players");
            default -> throw new IllegalArgumentException("Unsupported provider: " + provider);
        };
    }

    public Optional<DataProviderPlayer> playerById(String id, DataProvider provider) {
        return switch (provider) {
            case OTHER_API -> playerByIdFromOtherApi(id);
            case EXAMPLE_COM -> throw new UnsupportedOperationException("ExampleCom provider not supported for players");
            default -> throw new IllegalArgumentException("Unsupported provider: " + provider);
        };
    }

    private List<DataProviderPlayer> playersFromOtherApi() {
        return otherApiPlayerRepository.findAll().stream()
                .map(player -> new DataProviderPlayer(player.getId().toString(), player.getName(), player.getBirthDate()))
                .toList();
    }

    private Optional<DataProviderPlayer> playerByIdFromOtherApi(String id) {
        final var providerId = UUID.fromString(id);
        return otherApiPlayerRepository.findById(providerId)
                .map(player -> new DataProviderPlayer(player.getId().toString(), player.getName(), player.getBirthDate()));
    }
}
