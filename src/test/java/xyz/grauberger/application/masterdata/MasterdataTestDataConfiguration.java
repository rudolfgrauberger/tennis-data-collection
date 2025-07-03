package xyz.grauberger.application.masterdata;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import lombok.extern.slf4j.Slf4j;
import xyz.grauberger.application.masterdata.entities.Location;
import xyz.grauberger.application.masterdata.entities.Surface;
import xyz.grauberger.application.masterdata.entities.competition.Competition;
import xyz.grauberger.application.masterdata.entities.competition.PlayStyle;
import xyz.grauberger.application.masterdata.entities.match.DoubleMatch;
import xyz.grauberger.application.masterdata.entities.match.SingleMatch;
import xyz.grauberger.application.masterdata.entities.match.Team;
import xyz.grauberger.application.masterdata.entities.player.Gender;
import xyz.grauberger.application.masterdata.entities.player.Player;
import xyz.grauberger.application.masterdata.entities.tournament.Tournament;
import xyz.grauberger.application.masterdata.repositories.CompetitionRepository;
import xyz.grauberger.application.masterdata.repositories.MatchRepository;
import xyz.grauberger.application.masterdata.repositories.PlayerRepository;
import xyz.grauberger.application.masterdata.repositories.TournamentRepository;

@Slf4j
@TestConfiguration
public class MasterdataTestDataConfiguration {
    @Bean
    // Executes this before the application starts
    @Order(1)
    CommandLineRunner initMasterdata(PlayerRepository playerRepository,
                                    TournamentRepository tournamentRepository,
                                    CompetitionRepository competitionRepository,
                                    MatchRepository matchRepository) {
        return _ -> {
            log.info("Loading test samples for master data...");

            final var andreaVavassori = playerRepository.save(new Player("Andrea", "Vavassori", Gender.MEN, LocalDate.of(1995, 5, 5)));
            final var simoneBolelli = playerRepository.save(new Player("Simone", "Bolelli", Gender.MEN, LocalDate.of(1985, 10, 8)));
            final var hendrikJebens = playerRepository.save(new Player("Hendrik", "Jebens", Gender.MEN, LocalDate.of(1995, 6, 15)));
            final var corneliusFrantzen = playerRepository.save(new Player("Cornelius", "Frantzen", Gender.MEN, LocalDate.of(1998, 3, 22)));
            final var saraErrani = playerRepository.save(new Player("Sara", "Errani", Gender.WOMEN, LocalDate.of(1987, 4, 29)));
            final var ellenPerez = playerRepository.save(new Player("Ellen", "Perez", Gender.WOMEN, LocalDate.of(1995, 1, 10)));
            final var kevinKrawietz = playerRepository.save(new Player("Kevin", "Krawietz", Gender.MEN, LocalDate.of(1991, 11, 24)));
            final var taylorTownsend = playerRepository.save(new Player("Taylor", "Townsend", Gender.WOMEN, LocalDate.of(1996, 4, 16)));
            final var katerinaSiniakova = playerRepository.save(new Player("Kateřina", "Siniaková", Gender.WOMEN, LocalDate.of(1996, 5, 10)));
            final var fannyStollar = playerRepository.save(new Player("Fanny", "Stollár", Gender.WOMEN, LocalDate.of(1998, 11, 12)));
            final var luluSun = playerRepository.save(new Player("Lulu", "Sun", Gender.WOMEN, LocalDate.of(2001, 4, 14)));
            final var matteoBerrettini = playerRepository.save(new Player("Matteo", "Berrettini", Gender.MEN, LocalDate.of(1996, 4, 12)));
            final var cameronNorrie = playerRepository.save(new Player("Cameron", "Norrie", Gender.MEN, LocalDate.of(1995, 8, 23)));
            final var igaSwiatek = playerRepository.save(new Player("Iga", "Swiatek", Gender.WOMEN, LocalDate.of(2001, 5, 31)));

            final var tournaments = List.of(
                new Tournament("Australian Open", Surface.HARD, new Location("AUS", "Melbourne"), "Australian Open"),
                new Tournament("French Open", Surface.CLAY, new Location("FRA", "Paris"), "French Open"),
                new Tournament("Wimbledon", Surface.GRASS, new Location("GBR", "London"), "Wimbledon"),
                new Tournament("US Open", Surface.HARD, new Location("USA", "New York"), "US Open")
            );

            tournamentRepository.saveAll(tournaments);

            final var competitions = List.of(
                new Competition("Australian Open 2023", tournaments.get(0), PlayStyle.mensSingles(), "Australian Open 2023"),
                new Competition("French Open 2023", tournaments.get(1), PlayStyle.womensSingles(), "French Open 2023"),
                new Competition("Wimbledon 2023", tournaments.get(2), PlayStyle.mensDoubles(), "Wimbledon 2023"),
                new Competition("US Open 2023", tournaments.get(3), PlayStyle.womensDoubles(), "US Open 2023"),
                new Competition("ATP Finals 2023", PlayStyle.mensSingles(), Surface.HARD, new Location("ITA", "Turin"), "Start date: November 10"),
                new Competition("WTA Finals 2024", PlayStyle.womensSingles(), Surface.HARD, new Location("MEX", "Guadalajara"), "Start date: October 24"),
                new Competition("Olympic Games 2024", PlayStyle.mixedDoubles(), Surface.GRASS, new Location("FRA", "Paris"), "Olympic Games 2024")
            );

            competitionRepository.saveAll(competitions);

            final var australianOpen2025MensSingles = competitionRepository.save(new Competition("Australian Open 2025, Men's Singles", tournaments.get(0), PlayStyle.mensSingles(), ""));
            final var australianOpen2025WomensSingles = competitionRepository.save(new Competition("Australian Open 2025, Women's Singles", tournaments.get(0), PlayStyle.womensSingles(), ""));
            final var australianOpen2025MensDoubles = competitionRepository.save(new Competition("Australian Open 2025, Men's Doubles", tournaments.get(0), PlayStyle.mensDoubles(), ""));
            final var australianOpen2025WomensDoubles = competitionRepository.save(new Competition("Australian Open 2025, Women's Doubles", tournaments.get(0), PlayStyle.womensDoubles(), ""));
            final var australianOpen2025MixedDoubles = competitionRepository.save(new Competition("Australian Open 2025, Mixed Doubles", tournaments.get(0), PlayStyle.mixedDoubles(), ""));

            final var matches = List.of(
                new SingleMatch(
                    LocalDate.of(2025, 1, 14),
                    australianOpen2025MensSingles,
                    matteoBerrettini,
                    cameronNorrie
                ),
                new SingleMatch(
                    LocalDate.of(2023, 1, 13),
                    australianOpen2025WomensSingles,
                    igaSwiatek,
                    katerinaSiniakova
                ),
                // Mens doubles match
                new DoubleMatch(
                    LocalDate.of(2025, 1, 15),
                    australianOpen2025MensDoubles,
                    new Team(andreaVavassori, simoneBolelli),
                    new Team(hendrikJebens, corneliusFrantzen)
                ),
                // Women's doubles match
                new DoubleMatch(
                    LocalDate.of(2025, 1, 15),
                    australianOpen2025WomensDoubles,
                    new Team(taylorTownsend, katerinaSiniakova),
                    new Team(fannyStollar, luluSun)
                ),
                // Mixed doubles match
                new DoubleMatch(
                    LocalDate.of(2025, 1, 18),
                    australianOpen2025MixedDoubles,
                    new Team(andreaVavassori, saraErrani),
                    new Team(ellenPerez, kevinKrawietz)
                )
            );

            matchRepository.saveAll(matches);

            log.info("Master data test samples loaded successfully.");
        };
    }
}
