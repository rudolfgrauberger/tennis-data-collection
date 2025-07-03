package xyz.grauberger.application.idmapping;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import xyz.grauberger.application.fdi.provider.DataProvider;
import xyz.grauberger.application.idmapping.core.MapService;
import xyz.grauberger.application.masterdata.repositories.CompetitionRepository;
import xyz.grauberger.application.masterdata.repositories.MatchRepository;
import xyz.grauberger.application.masterdata.repositories.PlayerRepository;

@TestConfiguration
public class IdMappingTestDataTestConfiguration {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    record ProviderId(String id, DataProvider provider) {
        static ProviderId otherApi(String id) {
            return new ProviderId(id, DataProvider.OTHER_API);
        }

        static ProviderId exampleCom(String id) {
            return new ProviderId(id, DataProvider.EXAMPLE_COM);
        }
    }

    static final Map<String, List<ProviderId>> playerMappings = Map.ofEntries(
        Map.entry("Andrea Vavassori", List.of(ProviderId.otherApi("5e9e1479-8146-4623-9ef7-b69e749a13e8"))),
        Map.entry("Simone Bolelli", List.of(ProviderId.otherApi("dff04213-d7c5-4ad9-b4dd-fce3f68a5e01"))),
        Map.entry("Hendrik Jebens", List.of(ProviderId.otherApi("4cb536b4-d32a-4cc6-a46b-0a86b2adf5cb"))),
        Map.entry("Cornelius Frantzen", List.of(ProviderId.otherApi("1d2804b1-984e-4d36-96b7-a82925ed0c36"))),
        Map.entry("Sara Errani", List.of(ProviderId.otherApi("06f74090-28de-4e12-8ad8-4570fe61abba"))),
        Map.entry("Ellen Perez", List.of(ProviderId.otherApi("ace4c736-f827-4c9e-82bc-2c82e81eeff8"))),
        Map.entry("Kevin Krawietz", List.of(ProviderId.otherApi("58869e13-abf7-4ea7-a10c-69f04095b11d"))),
        Map.entry("Taylor Townsend", List.of(ProviderId.otherApi("f4df8a91-bc12-4bbd-b36f-00d84dbadd47"))),
        Map.entry("Kateřina Siniaková", List.of(ProviderId.otherApi("3d2f8b7c-5a49-42e6-91a3-72c5d4b79a8f"))),
        Map.entry("Fanny Stollár", List.of(ProviderId.otherApi("a577aca8-3af6-453d-a579-329a12c60e76"))),
        Map.entry("Lulu Sun", List.of(ProviderId.otherApi("033b0e95-8498-481b-ab15-f5491e2be421"))),
        Map.entry("Matteo Berrettini", List.of(ProviderId.otherApi("a3b4c5d6-e7f8-9012-abcd-ef1234567890"))),
        Map.entry("Cameron Norrie", List.of(ProviderId.otherApi("b3c4d5e6-f7a8-9012-abcd-ef1234567890"))),
        Map.entry("Iga Swiatek", List.of(ProviderId.otherApi("e7d3c9b2-4f5a-41a6-82c1-9b74a8d5f62c")))
    );

    static final Map<String, List<ProviderId>> matchMappings = Map.ofEntries(
        Map.entry("Matteo Berrettini vs. Cameron Norrie", List.of(
            ProviderId.otherApi("d98b9ee6-2e9c-45c8-bb9c-f23e5ecdf90c"),
            ProviderId.exampleCom("https://www.example.com/australian-open/herren/2025/matteo-berrettini_cameron-norrie")
        )),
        Map.entry("Iga Swiatek vs. Kateřina Siniaková", List.of(
            ProviderId.otherApi("f35c70cc-fb98-40a9-bf56-2c03889ee2ee"),
            ProviderId.exampleCom("https://www.example.com/australian-open/damen/2025/iga-swiatek_katerina-siniakova")
        )),
        Map.entry("Andrea Vavassori & Simone Bolelli vs. Hendrik Jebens & Cornelius Frantzen", List.of(
            ProviderId.otherApi("d7e8fb29-bc27-4881-86b4-d8fc21dcb6cd"),
            ProviderId.exampleCom("https://www.example.com/australian-open/herren-doppel/2025/simone-bolelli-andrea-vavassori_hendrik-jebens-cornelius-frantzen")
        )),
        Map.entry("Taylor Townsend & Kateřina Siniaková vs. Fanny Stollár & Lulu Sun", List.of(
            ProviderId.otherApi("6f2d8b13-91d1-45d6-b9e4-a9a0c5a9f2a3"),
            ProviderId.exampleCom("https://www.example.com/australian-open/damen-doppel/2025/katerina-siniakova-taylor-townsend_fanny-stollar-lulu-sun")
        )),
        Map.entry("Andrea Vavassori & Sara Errani vs. Ellen Perez & Kevin Krawietz", List.of(
            ProviderId.otherApi("3a4d6f9b-7e1c-4db6-84f2-a7b1c53a2d89"),
            ProviderId.exampleCom("https://www.example.com/australian-open/mixed/2025/sara-errani-andrea-vavassori_ellen-perez-kevin-krawietz")
        ))
    );

    static final Map<String, List<ProviderId>> competitionMappings = Map.ofEntries(
        Map.entry("Australian Open 2025, Men's Singles", List.of(
            ProviderId.otherApi("5922141e-56ad-4318-bf77-0f373dfd839d"),
            ProviderId.exampleCom("https://www.example.com/australian-open/herren/2025")
        )),
        Map.entry("Australian Open 2025, Women's Singles", List.of(
            ProviderId.otherApi("34a76c5e-c91d-4e47-82a1-8c974c436745"),
            ProviderId.exampleCom("https://www.example.com/australian-open/damen/2025")
        )),
        Map.entry("Australian Open 2025, Men's Doubles", List.of(
            ProviderId.otherApi("c4637c0d-4cd5-41ef-afc1-ac12eb788f44"),
            ProviderId.exampleCom("https://www.example.com/australian-open/herren-doppel/2025")
        )),
        Map.entry("Australian Open 2025, Women's Doubles", List.of(
            ProviderId.otherApi("59286657-7be8-489d-b160-484fecc319c5"),
            ProviderId.exampleCom("https://www.example.com/australian-open/damen-doppel/2025")
        )),
        Map.entry("Australian Open 2025, Mixed Doubles", List.of(
            ProviderId.otherApi("aa0f3a19-163f-4024-8a8e-fd29f75d744a"),
            ProviderId.exampleCom("https://www.example.com/australian-open/mixed/2025")
        ))
    );


    @Bean
    @Order(999) // Ensure this runs after other test data configurations
    CommandLineRunner initIdmappingTestData(PlayerRepository masterDataPlayerRepository,
            MatchRepository masterDataMatchRepository, CompetitionRepository masterdataCompetitionRepository,
            MapService mapService) {
        return _ -> {
            LOG.info("Loading test samples for ID mapping...");

            playerMappings.forEach((name, ids) -> {
                var player = masterDataPlayerRepository.findByName(name)
                        .orElseThrow(() -> new IllegalStateException("Player not found: " + name));

                ids.forEach(id -> mapService.mapPlayer(player.getId(), id.provider(), id.id()));
            });

            competitionMappings.forEach((name, ids) -> {
                var competition = masterdataCompetitionRepository.findByName(name)
                        .orElseThrow(() -> new IllegalStateException("Competition not found: " + name));

                ids.forEach(id -> mapService.mapCompetition(competition.getId(), id.provider(), id.id()));
            });

            matchMappings.forEach((name, ids) -> {
                var match = masterDataMatchRepository.findByName(name)
                        .orElseThrow(() -> new IllegalStateException("Match not found: " + name));

                ids.forEach(id -> mapService.mapMatch(match.getId(), id.provider(), id.id()));
            });

            LOG.info("Test samples for ID mapping loaded successfully.");
        };
    }
}
