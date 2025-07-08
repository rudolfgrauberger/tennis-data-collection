package xyz.grauberger.application.fdi;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import xyz.grauberger.application.fdi.provider.examplecom.repositories.ExampleComMatchRepository;
import xyz.grauberger.application.fdi.provider.otherapi.repositories.OtherApiMatchRepository;

@RequiredArgsConstructor
@Service
public class FdiMatchService {
    private final OtherApiMatchRepository otherApiMatchRepository;
    private final ExampleComMatchRepository exampleComMatchRepository;

    public LocalDate getLatestMatchDate(String matchId, DataProvider dataProvider) {
        switch (dataProvider) {
            case OTHER_API:
                final var mId = UUID.fromString(matchId); // Validate matchId format
                return otherApiMatchRepository.findById(mId).orElseThrow().getMatchDate();
            case EXAMPLE_COM:
                return exampleComMatchRepository.findById(matchId).orElseThrow().getMatchDate();
            default:
                throw new IllegalArgumentException("Unsupported data provider: " + dataProvider);
        }
    }
}
