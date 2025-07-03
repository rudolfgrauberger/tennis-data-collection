package xyz.grauberger.application.collection;

import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import lombok.extern.slf4j.Slf4j;
import xyz.grauberger.application.collection.management.entities.CollectionOrder;
import xyz.grauberger.application.collection.management.entities.MatchResult;
import xyz.grauberger.application.collection.management.repositories.CollectionOrderRepository;
import xyz.grauberger.application.collection.management.repositories.MatchResultRepository;
import xyz.grauberger.application.collection.tool.persistence.Event;
import xyz.grauberger.application.collection.tool.persistence.EventRepository;
import xyz.grauberger.application.collection.tool.persistence.EventType;
import xyz.grauberger.application.masterdata.repositories.MatchRepository;
import xyz.grauberger.application.masterdata.repositories.PlayerRepository;

@Slf4j
@TestConfiguration
public class CollectionTestDataTestConfiguration {

    /*
                new SingleMatch(
                    LocalDate.of(2025, 1, 14),
                    australianOpen2025MensSingles,
                    matteoBerrettini,
                    cameronNorrie
                )

                => https://www.eurosport.de/tennis/australian-open-herren/2025/live-matteo-berrettini-cameron-norrie_mtc1576429/live.shtml

                M. Berrettini   => 6^4 6 6 6
                C. Norrie       => 7^7 4 1 3

                // https://www.youtube.com/watch?v=voQxmDqNePA
                new DoubleMatch(
                    LocalDate.of(2025, 1, 15),
                    australianOpen2025WomensDoubles,
                    new Team(taylorTownsend, katerinaSiniakova),
                    new Team(fannyStollar, luluSun)
                ),

                => https://www.eurosport.de/tennis/australian-open-damen-doppel/2025/live-taylor-townsend-katerina-siniakova-fanny-stollar-lulu-sun_mtc1576704/live.shtml

                T. Townsend & K. Siniaková      => 6 6
                F. Stollár & L. Sun             => 2 4

     */

    @Bean
    // Executes this after masterdata is loaded
    @Order(2)
    CommandLineRunner initCollection(MatchRepository matchRepository,
                                    CollectionOrderRepository collectionOrderRepository,
                                    PlayerRepository playerRepository,
                                    EventRepository eventRepository,
                                    MatchResultRepository matchResultRepository) {
        return _ -> {
            log.info("Loading test samples for collection...");

            // Full match: https://www.youtube.com/watch?v=42ZLUeFr7TE
            final var match = matchRepository.findByName("Matteo Berrettini vs. Cameron Norrie")
                    .orElseThrow(() -> new IllegalArgumentException("Match not found: Matteo Berrettini vs. Cameron Norrie"));

            final var collectionOrder = new CollectionOrder(match, match.getMatchDate().atStartOfDay(ZoneId.of("UTC")).toInstant().plus(12, ChronoUnit.HOURS), "John Doe");
            collectionOrderRepository.save(collectionOrder);

            final var playerBerrettini = playerRepository.findByName("Matteo Berrettini")
                    .orElseThrow(() -> new IllegalArgumentException("Player not found: Matteo Berrettini"));
            final var playerNorrie = playerRepository.findByName("Cameron Norrie")
                    .orElseThrow(() -> new IllegalArgumentException("Player not found: Cameron Norrie"));

            final var firstRoundEvents = List.of(
                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // 15 : 0
                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // 15 : 15
                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // 30 : 15
                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // 40 : 15
                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // Winner Game 1

                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // 0 : 15
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // 0 : 30
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // 15 : 30
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // 30 : 30
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // 40 : 30
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // Winner Game 2

                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // 0 : 15
                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // 0 : 30
                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // 15 : 30
                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // 15 : 40
                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // 30 : 40
                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // 40 : 40
                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // AD
                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // Winner Game 3

                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // 15 : 0
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // 30 : 0
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // 40 : 0
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // Winner Game 4

                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // 15 : 0
                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // 15 : 15
                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // 30 : 15
                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // 40 : 15
                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // Winner Game 5

                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // 15 : 0
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // 15 : 15
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // 30 : 15
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // 30 : 30
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // 40 : 30
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // 40 : 40
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // AD
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // Winner Game 6

                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // 15 : 0
                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // 30 : 0
                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // 30 : 15
                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // 40 : 15
                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // 40 : 30
                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // Winner Game 7

                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // 15 : 0
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // 30 : 0
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // 40 : 0
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // Winner Game 8

                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // 15 : 0
                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // 30 : 0
                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // 40 : 0
                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // Winner Game 9

                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // 0 : 15
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // 15 : 15
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // 15 : 30
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // 30 : 30
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // 40 : 30
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // 40 : 40
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // AD
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // Winner Game 10

                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // 15 : 0
                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // 15 : 15
                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // 30 : 15
                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // 30 : 30
                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // 40 : 30
                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // Winner Game 11

                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // 15 : 0
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // 30 : 0
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // 30 : 15
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // 40 : 15
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // 40 : 30
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // 40 : 40
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // AD
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // Winner Game 12

                // 7 Point Tie-Break
                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // 1 : 0
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // 2 : 0
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // 2 : 1
                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // 3 : 1
                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerBerrettini, collectionOrder)),     // 4 : 1
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // 4 : 2
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // 4 : 3
                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // 4 : 4
                eventRepository.save(new Event(EventType.SERVE, playerBerrettini, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // 4 : 5
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder)),         // 4 : 6 => 2 Set Point
                eventRepository.save(new Event(EventType.SERVE, playerNorrie, collectionOrder)),
                eventRepository.save(new Event(EventType.WINS, playerNorrie, collectionOrder))          // 4 : 7 => Winner Tie-Break
            );

            eventRepository.saveAll(firstRoundEvents);

            matchResultRepository.save(new MatchResult(match.getId(), "6⁴:7⁷, 6:4, 6:1, 6:3"));
            collectionOrder.markAsCollected();
            collectionOrderRepository.save(collectionOrder);

            final var doublesMatch = matchRepository.findByName("Taylor Townsend & Kateřina Siniaková vs. Fanny Stollár & Lulu Sun")
                .orElseThrow(() -> new IllegalArgumentException("Match not found: Taylor Townsend & Kateřina Siniaková vs. Fanny Stollár & Lulu Sun"));

            final var doubleMatchCollectionOrder = new CollectionOrder(doublesMatch, doublesMatch.getMatchDate().atStartOfDay(ZoneId.of("UTC")).toInstant().plus(24, ChronoUnit.HOURS), "John Doe");
            collectionOrderRepository.save(doubleMatchCollectionOrder);


            log.info("Collection test samples loaded successfully.");
        };
    }

}
