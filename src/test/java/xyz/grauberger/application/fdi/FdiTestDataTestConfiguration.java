package xyz.grauberger.application.fdi;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import lombok.extern.slf4j.Slf4j;
import xyz.grauberger.application.fdi.provider.examplecom.entities.ExampleComCompetition;
import xyz.grauberger.application.fdi.provider.examplecom.entities.ExampleComMatch;
import xyz.grauberger.application.fdi.provider.examplecom.repositories.ExampleComCompetitionRepository;
import xyz.grauberger.application.fdi.provider.examplecom.repositories.ExampleComMatchRepository;
import xyz.grauberger.application.fdi.provider.otherapi.entities.OtherApiCompetition;
import xyz.grauberger.application.fdi.provider.otherapi.entities.OtherApiGender;
import xyz.grauberger.application.fdi.provider.otherapi.entities.OtherApiMatch;
import xyz.grauberger.application.fdi.provider.otherapi.entities.OtherApiPlayer;
import xyz.grauberger.application.fdi.provider.otherapi.repositories.OtherApiCompetitionRepository;
import xyz.grauberger.application.fdi.provider.otherapi.repositories.OtherApiMatchRepository;
import xyz.grauberger.application.fdi.provider.otherapi.repositories.OtherApiPlayerRepository;

/**
 * NOTE: This test configuration contains sample data that is partially random and partially generated
 * by AI autocompletion. The data provided here is neither complete nor correct and should only be
 * used for testing purposes. Do not rely on this data for accurate information about tennis tournaments,
 * players, or match results.
 */
@Slf4j
@TestConfiguration
public class FdiTestDataTestConfiguration {

    @Order(1)
    @Bean
    CommandLineRunner initFdiTestData(OtherApiPlayerRepository playerRepository,
            OtherApiCompetitionRepository competitionRepository,
            OtherApiMatchRepository matchRepository,
            ExampleComCompetitionRepository exampleComCompetitionRepository,
            ExampleComMatchRepository exampleComMatchRepository) {
        return _ -> {
            log.info("Loading test data for FDI...");

            loadExampleComTestData(exampleComCompetitionRepository, exampleComMatchRepository);
            loadOtherApiTestData(competitionRepository, matchRepository, playerRepository);

            // Additional test data can be loaded here if needed
            log.info("FDI data loaded successfully.");
        };
    }

    private void loadExampleComTestData(ExampleComCompetitionRepository competitionRepository,
            ExampleComMatchRepository matchRepository) {

        final var competitions = List.of(
            new ExampleComCompetition("https://www.example.com/australian-open/herren/2025", "Australian Open 2025, Herren", "Melbourne"),
            new ExampleComCompetition("https://www.example.com/australian-open/herren/2024", "Australian Open 2024, Herren", "Melbourne"),
            new ExampleComCompetition("https://www.example.com/australian-open/herren/2023", "Australian Open 2023, Herren", "Melbourne"),
            new ExampleComCompetition("https://www.example.com/australian-open/damen/2025", "Australian Open 2025, Damen", "Melbourne"),
            new ExampleComCompetition("https://www.example.com/australian-open/damen/2024", "Australian Open 2024, Damen", "Melbourne"),
            new ExampleComCompetition("https://www.example.com/australian-open/damen/2023", "Australian Open 2023, Damen", "Melbourne"),
            new ExampleComCompetition("https://www.example.com/australian-open/herren-doppel/2025", "Australian Open 2025, Herren-Doppel", "Melbourne"),
            new ExampleComCompetition("https://www.example.com/australian-open/herren-doppel/2024", "Australian Open 2024, Herren-Doppel", "Melbourne"),
            new ExampleComCompetition("https://www.example.com/australian-open/herren-doppel/2023", "Australian Open 2023, Herren-Doppel", "Melbourne"),
            new ExampleComCompetition("https://www.example.com/australian-open/damen-doppel/2025", "Australian Open 2025, Damen-Doppel", "Melbourne"),
            new ExampleComCompetition("https://www.example.com/australian-open/damen-doppel/2024", "Australian Open 2024, Damen-Doppel", "Melbourne"),
            new ExampleComCompetition("https://www.example.com/australian-open/damen-doppel/2023", "Australian Open 2023, Damen-Doppel", "Melbourne"),
            new ExampleComCompetition("https://www.example.com/australian-open/mixed/2025", "Australian Open 2025, Mixed", "Melbourne"),
            new ExampleComCompetition("https://www.example.com/australian-open/mixed/2024", "Australian Open 2024, Mixed", "Melbourne"),
            new ExampleComCompetition("https://www.example.com/australian-open/mixed/2023", "Australian Open 2023, Mixed", "Melbourne"),

            new ExampleComCompetition("https://www.example.com/french-open/herren/2025", "French Open 2025, Herren", "Paris"),
            new ExampleComCompetition("https://www.example.com/french-open/herren/2024", "French Open 2024, Herren", "Paris"),
            new ExampleComCompetition("https://www.example.com/french-open/herren/2023", "French Open 2023, Herren", "Paris"),
            new ExampleComCompetition("https://www.example.com/french-open/damen/2025", "French Open 2025, Damen", "Paris"),
            new ExampleComCompetition("https://www.example.com/french-open/damen/2024", "French Open 2024, Damen", "Paris"),
            new ExampleComCompetition("https://www.example.com/french-open/damen/2023", "French Open 2023, Damen", "Paris"),
            new ExampleComCompetition("https://www.example.com/french-open/herren-doppel/2025", "French Open 2025, Herren-Doppel", "Paris"),
            new ExampleComCompetition("https://www.example.com/french-open/herren-doppel/2024", "French Open 2024, Herren-Doppel", "Paris"),
            new ExampleComCompetition("https://www.example.com/french-open/herren-doppel/2023", "French Open 2023, Herren-Doppel", "Paris"),
            new ExampleComCompetition("https://www.example.com/french-open/damen-doppel/2025", "French Open 2025, Damen-Doppel", "Paris"),
            new ExampleComCompetition("https://www.example.com/french-open/damen-doppel/2024", "French Open 2024, Damen-Doppel", "Paris"),
            new ExampleComCompetition("https://www.example.com/french-open/damen-doppel/2023", "French Open 2023, Damen-Doppel", "Paris"),
            new ExampleComCompetition("https://www.example.com/french-open/mixed/2025", "French Open 2025, Mixed", "Paris"),
            new ExampleComCompetition("https://www.example.com/french-open/mixed/2024", "French Open 2024, Mixed", "Paris"),
            new ExampleComCompetition("https://www.example.com/french-open/mixed/2023", "French Open 2023, Mixed", "Paris"),

            new ExampleComCompetition("https://www.example.com/wimbledon/herren/2025", "Wimbledon 2025, Herren", "London"),
            new ExampleComCompetition("https://www.example.com/wimbledon/herren/2024", "Wimbledon 2024, Herren", "London"),
            new ExampleComCompetition("https://www.example.com/wimbledon/herren/2023", "Wimbledon 2023, Herren", "London"),
            new ExampleComCompetition("https://www.example.com/wimbledon/damen/2025", "Wimbledon 2025, Damen", "London"),
            new ExampleComCompetition("https://www.example.com/wimbledon/damen/2024", "Wimbledon 2024, Damen", "London"),
            new ExampleComCompetition("https://www.example.com/wimbledon/damen/2023", "Wimbledon 2023, Damen", "London"),
            new ExampleComCompetition("https://www.example.com/wimbledon/herren-doppel/2025", "Wimbledon 2025, Herren-Doppel", "London"),
            new ExampleComCompetition("https://www.example.com/wimbledon/herren-doppel/2024", "Wimbledon 2024, Herren-Doppel", "London"),
            new ExampleComCompetition("https://www.example.com/wimbledon/herren-doppel/2023", "Wimbledon 2023, Herren-Doppel", "London"),
            new ExampleComCompetition("https://www.example.com/wimbledon/damen-doppel/2025", "Wimbledon 2025, Damen-Doppel", "London"),
            new ExampleComCompetition("https://www.example.com/wimbledon/damen-doppel/2024", "Wimbledon 2024, Damen-Doppel", "London"),
            new ExampleComCompetition("https://www.example.com/wimbledon/damen-doppel/2023", "Wimbledon 2023, Damen-Doppel", "London"),
            new ExampleComCompetition("https://www.example.com/wimbledon/mixed/2025", "Wimbledon 2025, Mixed", "London"),
            new ExampleComCompetition("https://www.example.com/wimbledon/mixed/2024", "Wimbledon 2024, Mixed", "London"),
            new ExampleComCompetition("https://www.example.com/wimbledon/mixed/2023", "Wimbledon 2023, Mixed", "London"),

            new ExampleComCompetition("https://www.example.com/us-open/herren/2025", "US Open 2025, Herren", "New York"),
            new ExampleComCompetition("https://www.example.com/us-open/herren/2024", "US Open 2024, Herren", "New York"),
            new ExampleComCompetition("https://www.example.com/us-open/herren/2023", "US Open 2023, Herren", "New York"),
            new ExampleComCompetition("https://www.example.com/us-open/damen/2025", "US Open 2025, Damen", "New York"),
            new ExampleComCompetition("https://www.example.com/us-open/damen/2024", "US Open 2024, Damen", "New York"),
            new ExampleComCompetition("https://www.example.com/us-open/damen/2023", "US Open 2023, Damen", "New York"),
            new ExampleComCompetition("https://www.example.com/us-open/herren-doppel/2025", "US Open 2025, Herren-Doppel", "New York"),
            new ExampleComCompetition("https://www.example.com/us-open/herren-doppel/2024", "US Open 2024, Herren-Doppel", "New York"),
            new ExampleComCompetition("https://www.example.com/us-open/herren-doppel/2023", "US Open 2023, Herren-Doppel", "New York"),
            new ExampleComCompetition("https://www.example.com/us-open/damen-doppel/2025", "US Open 2025, Damen-Doppel", "New York"),
            new ExampleComCompetition("https://www.example.com/us-open/damen-doppel/2024", "US Open 2024, Damen-Doppel", "New York"),
            new ExampleComCompetition("https://www.example.com/us-open/damen-doppel/2023", "US Open 2023, Damen-Doppel", "New York"),
            new ExampleComCompetition("https://www.example.com/us-open/mixed/2025", "US Open 2025, Mixed", "New York"),
            new ExampleComCompetition("https://www.example.com/us-open/mixed/2024", "US Open 2024, Mixed", "New York"),
            new ExampleComCompetition("https://www.example.com/us-open/mixed/2023", "US Open 2023, Mixed", "New York")
        );

        competitionRepository.saveAll(competitions);

        final var australianOpen2025Men = competitionRepository.findById("https://www.example.com/australian-open/herren/2025").get();
        final var australianOpen2025Women = competitionRepository.findById("https://www.example.com/australian-open/damen/2025").get();
        final var australianOpen2025MenDoubles = competitionRepository.findById("https://www.example.com/australian-open/herren-doppel/2025").get();
        final var australianOpen2025WomenDoubles = competitionRepository.findById("https://www.example.com/australian-open/damen-doppel/2025").get();
        final var australianOpen2025Mixed = competitionRepository.findById("https://www.example.com/australian-open/mixed/2025").get();

        final var matches = List.of(
            // Australian Open 2025, Herren
            new ExampleComMatch("https://www.example.com/australian-open/herren/2025/jannik-sinner_nicolas-jarry", "Jannik Sinner vs. Nicolás Jarry", LocalDate.of(2025, 1, 13), australianOpen2025Men),
            new ExampleComMatch("https://www.example.com/australian-open/herren/2025/tristan-schoolkate_taro-daniel", "Tristan Schoolkate vs. Taro Daniel", LocalDate.of(2025, 1, 13), australianOpen2025Men),
            new ExampleComMatch("https://www.example.com/australian-open/herren/2025/marcos-giron_yannick-hanfmann", "Marcos Giron vs. Yannick Hanfmann", LocalDate.of(2025, 1, 13), australianOpen2025Men),
            new ExampleComMatch("https://www.example.com/australian-open/herren/2025/tomas-etcheverry_flavio-cobolli", "Tomás Etcheverry vs. Flavio Cobolli", LocalDate.of(2025, 1, 13), australianOpen2025Men),
            new ExampleComMatch("https://www.example.com/australian-open/herren/2025/hubert-hurkacz_tallon-griekspoor", "Hubert Hurkacz vs. Tallon Griekspoor", LocalDate.of(2025, 1, 13), australianOpen2025Men),
            new ExampleComMatch("https://www.example.com/australian-open/herren/2025/miomir-kecmanovic_dusan-lajovic", "Miomir Kecmanovic vs. Dušan Lajovic", LocalDate.of(2025, 1, 13), australianOpen2025Men),
            new ExampleComMatch("https://www.example.com/australian-open/herren/2025/matteo-berrettini_cameron-norrie", "Matteo Berrettini vs. Cameron Norrie", LocalDate.of(2025, 1, 13), australianOpen2025Men),
            new ExampleComMatch("https://www.example.com/australian-open/herren/2025/zhizhen-zhang_holger-rune", "Zhizhen Zhang vs. Holger Rune", LocalDate.of(2025, 1, 13), australianOpen2025Men),
            new ExampleComMatch("https://www.example.com/australian-open/herren/2025/stefanos-tsitsipas_alex-michelsen", "Stefanos Tsitsipas vs. Alex Michelsen", LocalDate.of(2025, 1, 13), australianOpen2025Men),
            new ExampleComMatch("https://www.example.com/australian-open/herren/2025/james-mccabe_martin-landaluce", "James McCabe vs. Martín Landaluce", LocalDate.of(2025, 1, 13), australianOpen2025Men),
            new ExampleComMatch("https://www.example.com/australian-open/herren/2025/gabriel-diallo_luca-nardi", "Gabriel Diallo vs. Luca Nardi", LocalDate.of(2025, 1, 13), australianOpen2025Men),
            new ExampleComMatch("https://www.example.com/australian-open/herren/2025/adrian-mannarino_karen-khachanov", "Adrian Mannarino vs. Karen Khachanov", LocalDate.of(2025, 1, 13), australianOpen2025Men),
            new ExampleComMatch("https://www.example.com/australian-open/herren/2025/francisco-cerundolo_alexander-bublik", "Francisco Cerúndolo vs. Alexander Bublik", LocalDate.of(2025, 1, 13), australianOpen2025Men),
            new ExampleComMatch("https://www.example.com/australian-open/herren/2025/facundo-diaz_zizou-bergs", "Facundo Díaz vs. Zizou Bergs", LocalDate.of(2025, 1, 13), australianOpen2025Men),
            new ExampleComMatch("https://www.example.com/australian-open/herren/2025/tristan-boyer_federico-coria", "Tristan Boyer vs. Federico Coria", LocalDate.of(2025, 1, 13), australianOpen2025Men),
            new ExampleComMatch("https://www.example.com/australian-open/herren/2025/botic-van-de-zandschulp_alex-de-minaur", "Botic van de Zandschulp vs. Alex De Minaur", LocalDate.of(2025, 1, 13), australianOpen2025Men),
            new ExampleComMatch("https://www.example.com/australian-open/herren/2025/taylor-fritz_jenson-brooksby", "Taylor Fritz vs. Jenson Brooksby", LocalDate.of(2025, 1, 13), australianOpen2025Men),
            new ExampleComMatch("https://www.example.com/australian-open/herren/2025/borna-coric_cristian-garin", "Borna Coric vs. Cristian Garín", LocalDate.of(2025, 1, 13), australianOpen2025Men),
            new ExampleComMatch("https://www.example.com/australian-open/herren/2025/francisco-comesana_daniel-altmaier", "Francisco Comesaña vs. Daniel Altmaier", LocalDate.of(2025, 1, 13), australianOpen2025Men),
            new ExampleComMatch("https://www.example.com/australian-open/herren/2025/gael-monfils_giovanni-mpetshi-perricard", "Gaël Monfils vs. Giovanni Mpetshi Perricard", LocalDate.of(2025, 1, 13), australianOpen2025Men),
            new ExampleComMatch("https://www.example.com/australian-open/herren/2025/ben-shelton_brandon-nakashima", "Ben Shelton vs. Brandon Nakashima", LocalDate.of(2025, 1, 13), australianOpen2025Men),
            new ExampleComMatch("https://www.example.com/australian-open/herren/2025/pablo-carreno_kamil-majchrzak", "Pablo Carreño vs. Kamil Majchrzak", LocalDate.of(2025, 1, 13), australianOpen2025Men),
            new ExampleComMatch("https://www.example.com/australian-open/herren/2025/roberto-bautista_denis-shapovalov", "Roberto Bautista vs. Denis Shapovalov", LocalDate.of(2025, 1, 13), australianOpen2025Men),
            new ExampleComMatch("https://www.example.com/australian-open/herren/2025/matteo-arnaldi_lorenzo-musetti", "Matteo Arnaldi vs. Lorenzo Musetti", LocalDate.of(2025, 1, 13), australianOpen2025Men),
            new ExampleComMatch("https://www.example.com/australian-open/herren/2025/andrey-rublev_joao-fonseca", "Andrey Rublev vs. João Fonseca", LocalDate.of(2025, 1, 13), australianOpen2025Men),

            // Australian Open 2025, Damen
            new ExampleComMatch("https://www.example.com/australian-open/damen/2025/aryna-sabalenka_sloane-stephens", "Aryna Sabalenka vs. Sloane Stephens", LocalDate.of(2025, 1, 13), australianOpen2025Women),
            new ExampleComMatch("https://www.example.com/australian-open/damen/2025/naomi-osaka_caroline-garcia", "Naomi Osaka vs. Caroline Garcia", LocalDate.of(2025, 1, 13), australianOpen2025Women),
            new ExampleComMatch("https://www.example.com/australian-open/damen/2025/coco-gauff_sofia-kenin", "Coco Gauff vs. Sofia Kenin", LocalDate.of(2025, 1, 13), australianOpen2025Women),
            new ExampleComMatch("https://www.example.com/australian-open/damen/2025/iga-swiatek_katerina-siniakova", "Iga Swiatek vs. Katerina Siniakova", LocalDate.of(2025, 1, 13), australianOpen2025Women),
            new ExampleComMatch("https://www.example.com/australian-open/damen/2025/linda-noskova_clara-tauson", "Linda Noskova vs. Clara Tauson", LocalDate.of(2025, 1, 13), australianOpen2025Women),
            new ExampleComMatch("https://www.example.com/australian-open/damen/2025/jelena-ostapenko_belinda-bencic", "Jelena Ostapenko vs. Belinda Bencic", LocalDate.of(2025, 1, 13), australianOpen2025Women),
            new ExampleComMatch("https://www.example.com/australian-open/damen/2025/sorana-cirstea_elina-svitolina", "Sorana Cirstea vs. Elina Svitolina", LocalDate.of(2025, 1, 13), australianOpen2025Women),
            new ExampleComMatch("https://www.example.com/australian-open/damen/2025/emma-raducanu_ekaterina-alexandrova", "Emma Raducanu vs. Ekaterina Alexandrova", LocalDate.of(2025, 1, 13), australianOpen2025Women),
            new ExampleComMatch("https://www.example.com/australian-open/damen/2025/emma-navarro_peyton-stearns", "Emma Navarro vs. Peyton Stearns", LocalDate.of(2025, 1, 13), australianOpen2025Women),
            new ExampleComMatch("https://www.example.com/australian-open/damen/2025/ons-jabeur_anhelina-kalinina", "Ons Jabeur vs. Anhelina Kalinina", LocalDate.of(2025, 1, 13), australianOpen2025Women),

            // Australian Open 2025, Herren-Doppel
            new ExampleComMatch("https://www.example.com/australian-open/herren-doppel/2025/marcelo-arevalo-mate-pavic_jason-kubler-rinky-hijikata", "Marcelo Arévalo/Mate Pavic vs. Jason Kubler/Rinky Hijikata", LocalDate.of(2025, 1, 16), australianOpen2025MenDoubles),
            new ExampleComMatch("https://www.example.com/australian-open/herren-doppel/2025/damir-dzumhur-petros-tsitsipas_kamil-drzewiecki-abhishek-chandrasekar", "Damir Dzumhur/Petros Tsitsipas vs. Kamil Drzewiecki/Abhishek Chandrasekar", LocalDate.of(2025, 1, 16), australianOpen2025MenDoubles),
            new ExampleComMatch("https://www.example.com/australian-open/herren-doppel/2025/pablo-carreno-busta-sergio-martos-gornes_sander-arends-luke-johnson", "Pablo Carreno Busta/Sergio Martos Gornes vs. Sander Arends/Luke Johnson", LocalDate.of(2025, 1, 16), australianOpen2025MenDoubles),
            new ExampleComMatch("https://www.example.com/australian-open/herren-doppel/2025/petr-nouza-patrik-rikl_sadio-doumbia-florian-reboul", "Petr Nouza/Patrik Rikl vs. Sadio Doumbia/Florian Reboul", LocalDate.of(2025, 1, 16), australianOpen2025MenDoubles),
            new ExampleComMatch("https://www.example.com/australian-open/herren-doppel/2025/joe-salisbury-neal-skupski_sebastian-baez-francisco-comesana", "Joe Salisbury/Neal Skupski vs. Sebastian Baez/Francisco Comesana", LocalDate.of(2025, 1, 16), australianOpen2025MenDoubles),
            new ExampleComMatch("https://www.example.com/australian-open/herren-doppel/2025/andreas-goransson-sander-verbeek_alexander-erler-andreas-mies", "Andreas Goransson/Sander Verbeek vs. Alexander Erler/Andreas Mies", LocalDate.of(2025, 1, 16), australianOpen2025MenDoubles),
            new ExampleComMatch("https://www.example.com/australian-open/herren-doppel/2025/james-duckworth-alexander-vukic_thanasi-kokkinakis-nick-kyrgios", "James Duckworth/Alexander Vukic vs. Thanasi Kokkinakis/Nick Kyrgios", LocalDate.of(2025, 1, 16), australianOpen2025MenDoubles),
            new ExampleComMatch("https://www.example.com/australian-open/herren-doppel/2025/luke-saville-li-tu_maximo-gonzalez-andres-molteni", "Luke Saville/Li Tu vs. Maximo Gonzalez/Andres Molteni", LocalDate.of(2025, 1, 16), australianOpen2025MenDoubles),
            new ExampleComMatch("https://www.example.com/australian-open/herren-doppel/2025/simone-bolelli-andrea-vavassori_hendrik-jebens-cornelius-frantzen", "Simone Bolelli/Andrea Vavassori vs. Hendrik Jebens/Cornelius Frantzen", LocalDate.of(2025, 1, 16), australianOpen2025MenDoubles),
            new ExampleComMatch("https://www.example.com/australian-open/herren-doppel/2025/marcelo-bortolotti-flavio-cobolli_daniel-hidalgo-luciano-darderi", "Marcelo Bortolotti/Flavio Cobolli vs. Daniel Hidalgo/Luciano Darderi", LocalDate.of(2025, 1, 16), australianOpen2025MenDoubles),
            new ExampleComMatch("https://www.example.com/australian-open/herren-doppel/2025/adam-pavlasek-jean-julien-rojer_romain-arneodo-arthur-cazaux", "Adam Pavlásek/Jean-Julien Rojer vs. Romain Arneodo/Arthur Cazaux", LocalDate.of(2025, 1, 16), australianOpen2025MenDoubles),

            // Australian Open 2025, Damen-Doppel
            new ExampleComMatch("https://www.example.com/australian-open/damen-doppel/2025/katerina-siniakova-taylor-townsend_fanny-stollar-lulu-sun", "Katerina Siniakova/Taylor Townsend vs. Fanny Stollár/Lulu Sun", LocalDate.of(2025, 1, 16), australianOpen2025WomenDoubles),
            new ExampleComMatch("https://www.example.com/australian-open/damen-doppel/2025/veronika-kudermetova-elise-mertens_gabriela-dabrowski-erin-routliffe", "Veronika Kudermetova/Elise Mertens vs. Gabriela Dabrowski/Erin Routliffe", LocalDate.of(2025, 1, 16), australianOpen2025WomenDoubles),
            new ExampleComMatch("https://www.example.com/australian-open/damen-doppel/2025/luisa-stefani-peyton-stearns_irina-begu-inga-gamarra-martins", "Luisa Stefani/Peyton Stearns vs. Irina Begu/Inga Gamarra Martins", LocalDate.of(2025, 1, 16), australianOpen2025WomenDoubles),
            new ExampleComMatch("https://www.example.com/australian-open/damen-doppel/2025/alycia-parks-qianhui-tang_yafan-wang-shuai-zheng", "Alycia Parks/Qianhui Tang vs. Yafan Wang/Shuai Zheng", LocalDate.of(2025, 1, 16), australianOpen2025WomenDoubles),
            new ExampleComMatch("https://www.example.com/australian-open/damen-doppel/2025/sara-errani-jasmine-paolini_priscilla-hon-daria-saville", "Sara Errani/Jasmine Paolini vs. Priscilla Hon/Daria Saville", LocalDate.of(2025, 1, 16), australianOpen2025WomenDoubles),
            new ExampleComMatch("https://www.example.com/australian-open/damen-doppel/2025/marta-kostyuk-elena-gabriela-ruse_destanee-aiava-maddison-inglis", "Marta Kostyuk/Elena Gabriela Ruse vs. Destanee Aiava/Maddison Inglis", LocalDate.of(2025, 1, 16), australianOpen2025WomenDoubles),
            new ExampleComMatch("https://www.example.com/australian-open/damen-doppel/2025/laura-siegemund-beatriz-haddad-maia_quinn-gleason-suzan-lamens", "Laura Siegemund/Beatriz Haddad Maia vs. Quinn Gleason/Suzan Lamens", LocalDate.of(2025, 1, 16), australianOpen2025WomenDoubles),
            new ExampleComMatch("https://www.example.com/australian-open/damen-doppel/2025/miyu-kato-renata-zarazua_sofia-kenin-monica-niculescu", "Miyu Kato/Renata Zarazua vs. Sofia Kenin/Monica Niculescu", LocalDate.of(2025, 1, 16), australianOpen2025WomenDoubles),
            new ExampleComMatch("https://www.example.com/australian-open/damen-doppel/2025/kristina-mladenovic-shuai-zhang_mccartney-kessler-arantxa-rus", "Kristina Mladenovic/Shuai Zhang vs. McCartney Kessler/Arantxa Rus", LocalDate.of(2025, 1, 16), australianOpen2025WomenDoubles),
            new ExampleComMatch("https://www.example.com/australian-open/damen-doppel/2025/varvara-gracheva-oxana-kalashnikova_maia-lumsden-anna-siskova", "Varvara Gracheva/Oxana Kalashnikova vs. Maia Lumsden/Anna Siskova", LocalDate.of(2025, 1, 16), australianOpen2025WomenDoubles),

            // Australian Open 2025, Mixed
            new ExampleComMatch("https://www.example.com/australian-open/mixed/2025/sara-errani-andrea-vavassori_ellen-perez-kevin-krawietz", "Sara Errani/Andrea Vavassori vs. Ellen Perez/Kevin Krawietz", LocalDate.of(2025, 1, 16), australianOpen2025Mixed),
            new ExampleComMatch("https://www.example.com/australian-open/mixed/2025/taylor-townsend-hugo-nys_desirae-krawczyk-neal-skupski", "Taylor Townsend/Hugo Nys vs. Desirae Krawczyk/Neal Skupski", LocalDate.of(2025, 1, 16), australianOpen2025Mixed),
            new ExampleComMatch("https://www.example.com/australian-open/mixed/2025/hsieh-su-wei-jan-zielinski_demi-schuurs-tim-puetz", "Hsieh Su-wei/Jan Zielinski vs. Demi Schuurs/Tim Pütz", LocalDate.of(2025, 1, 16), australianOpen2025Mixed),
            new ExampleComMatch("https://www.example.com/australian-open/mixed/2025/asia-muhammad-andres-molteni_olivia-gadecki-john-peers", "Asia Muhammad/Andres Molteni vs. Olivia Gadecki/John Peers", LocalDate.of(2025, 1, 16), australianOpen2025Mixed),
            new ExampleComMatch("https://www.example.com/australian-open/mixed/2025/kimberly-birrell-john-patrick-smith_olivia-nicholls-henry-patten", "Kimberly Birrell/John-Patrick Smith vs. Olivia Nicholls/Henry Patten", LocalDate.of(2025, 1, 16), australianOpen2025Mixed),
            new ExampleComMatch("https://www.example.com/australian-open/mixed/2025/erin-routliffe-michael-venus_giuliana-olmos-marcel-granollers", "Erin Routliffe/Michael Venus vs. Giuliana Olmos/Marcel Granollers", LocalDate.of(2025, 1, 16), australianOpen2025Mixed),
            new ExampleComMatch("https://www.example.com/australian-open/mixed/2025/sadie-engelbrecht-luke-saville_miyu-kato-ben-mclachlan", "Sadie Engelbrecht/Luke Saville vs. Miyu Kato/Ben McLachlan", LocalDate.of(2025, 1, 16), australianOpen2025Mixed),
            new ExampleComMatch("https://www.example.com/australian-open/mixed/2025/arantxa-rus-marcelo-arevalo_kaitlyn-christian-nathaniel-lammons", "Arantxa Rus/Marcelo Arévalo vs. Kaitlyn Christian/Nathaniel Lammons", LocalDate.of(2025, 1, 16), australianOpen2025Mixed),
            new ExampleComMatch("https://www.example.com/australian-open/mixed/2025/monica-niculescu-bernabe-zapata-miralles_ana-bogdan-alexander-bublik", "Monica Niculescu/Bernabe Zapata Miralles vs. Ana Bogdan/Alexander Bublik", LocalDate.of(2025, 1, 16), australianOpen2025Mixed),
            new ExampleComMatch("https://www.example.com/australian-open/mixed/2025/anna-danilina-harri-heliovaara_gabriela-dabrowski-sander-gille", "Anna Danilina/Harri Heliövaara vs. Gabriela Dabrowski/Sander Gillé", LocalDate.of(2025, 1, 16), australianOpen2025Mixed)
        );

        matchRepository.saveAll(matches);
    }

    private void loadOtherApiTestData(OtherApiCompetitionRepository competitionRepository,
            OtherApiMatchRepository matchRepository, OtherApiPlayerRepository playerRepository) {


        final var competitions = List.of(
            new OtherApiCompetition(UUID.fromString("5922141e-56ad-4318-bf77-0f373dfd839d"), "Australian Open 2025, Herren"),
            new OtherApiCompetition(UUID.fromString("06c3d108-3cb9-419a-9bd8-3682f5d333fd"), "Australian Open 2024, Herren"),
            new OtherApiCompetition(UUID.fromString("34a76c5e-c91d-4e47-82a1-8c974c436745"), "Australian Open 2025, Damen"),
            new OtherApiCompetition(UUID.fromString("2668a41a-c226-4a72-82c7-25ce20d74b5a"), "Australian Open 2024, Damen"),
            new OtherApiCompetition(UUID.fromString("c4637c0d-4cd5-41ef-afc1-ac12eb788f44"), "Australian Open 2025, Herren-Doppel"),
            new OtherApiCompetition(UUID.fromString("441748b9-6009-442e-8d3a-67fa08311686"), "Australian Open 2024, Herren-Doppel"),
            new OtherApiCompetition(UUID.fromString("59286657-7be8-489d-b160-484fecc319c5"), "Australian Open 2025, Damen-Doppel"),
            new OtherApiCompetition(UUID.fromString("4623bd18-cf95-41a4-a0fd-70590a9930e7"), "Australian Open 2024, Damen-Doppel"),
            new OtherApiCompetition(UUID.fromString("aa0f3a19-163f-4024-8a8e-fd29f75d744a"), "Australian Open 2025, Mixed"),
            new OtherApiCompetition(UUID.fromString("61c9dfd1-9d4a-4246-b805-2ab67fd9e68d"), "Australian Open 2024, Mixed"),

            new OtherApiCompetition(UUID.fromString("286c0036-c485-4271-9891-650f5db11a38"), "French Open 2025, Herren"),
            new OtherApiCompetition(UUID.fromString("e9bbcb1e-af64-45c7-8f98-78bfc7fa799a"), "French Open 2024, Herren"),
            new OtherApiCompetition(UUID.fromString("d2b1f08e-c12b-4f02-bf54-76ce5e8bf7f3"), "French Open 2025, Damen"),
            new OtherApiCompetition(UUID.fromString("e112b278-e63e-43ad-b156-31c82004faa1"), "French Open 2024, Damen"),
            new OtherApiCompetition(UUID.fromString("4727d992-59c5-4f7d-8f9b-7d09fbc71fb8"), "French Open 2025, Herren-Doppel"),
            new OtherApiCompetition(UUID.fromString("e3dc9fe9-4485-4b9b-b038-da91e3826b7d"), "French Open 2024, Herren-Doppel"),
            new OtherApiCompetition(UUID.fromString("8e2ea20e-1bf3-4977-b0fe-fc738fa58498"), "French Open 2025, Damen-Doppel"),
            new OtherApiCompetition(UUID.fromString("a1ad630f-8fd3-487f-9336-6a701dc5ac26"), "French Open 2024, Damen-Doppel"),
            new OtherApiCompetition(UUID.fromString("6ac02c97-8c27-4774-aac9-0044cce86dcd"), "French Open 2025, Mixed"),
            new OtherApiCompetition(UUID.fromString("1a56d7bc-93c0-42e6-83de-277c1c87c114"), "French Open 2024, Mixed"),

            new OtherApiCompetition(UUID.fromString("285907b8-6d61-4496-86c6-56cf7e7e3455"), "Wimbledon 2025, Herren"),
            new OtherApiCompetition(UUID.fromString("d62ab6dd-513b-47eb-9967-3f13c0c5cd43"), "Wimbledon 2024, Herren"),
            new OtherApiCompetition(UUID.fromString("b775737b-e75b-4ed3-a7a6-e8e9c449f1d6"), "Wimbledon 2025, Damen"),
            new OtherApiCompetition(UUID.fromString("ed668b21-3fd7-4a5a-b295-27c7ed0a8223"), "Wimbledon 2024, Damen"),
            new OtherApiCompetition(UUID.fromString("9c2db6c6-d858-4c56-900c-eed0e099a0d4"), "Wimbledon 2025, Herren-Doppel"),
            new OtherApiCompetition(UUID.fromString("3b2740f4-0f3d-4d60-ab76-02771aea18fd"), "Wimbledon 2024, Herren-Doppel"),
            new OtherApiCompetition(UUID.fromString("42fec3a9-cf6d-4432-be03-07a17db12500"), "Wimbledon 2025, Damen-Doppel"),
            new OtherApiCompetition(UUID.fromString("917ce48b-8fd3-41c3-a74e-a77b3f701321"), "Wimbledon 2024, Damen-Doppel"),
            new OtherApiCompetition(UUID.fromString("b0fb9ad0-d712-4d06-b05c-3f1a866e53b6"), "Wimbledon 2025, Mixed"),
            new OtherApiCompetition(UUID.fromString("d855b147-6bee-4f70-b456-88d95c105ca8"), "Wimbledon 2024, Mixed"),

            new OtherApiCompetition(UUID.fromString("22269460-84ab-43dc-8307-84a936e46f17"), "US Open 2025, Herren"),
            new OtherApiCompetition(UUID.fromString("74f26fd9-f998-48e7-9e6e-13a9c6bb5bf7"), "US Open 2024, Herren"),
            new OtherApiCompetition(UUID.fromString("f46b6a83-4a63-43c4-b395-6d58b307e2d6"), "US Open 2025, Damen"),
            new OtherApiCompetition(UUID.fromString("ea4e003d-c232-4b2c-a424-8fccc7eb59a8"), "US Open 2024, Damen"),
            new OtherApiCompetition(UUID.fromString("ac6e7725-3c24-4fd9-a56f-eaf96301a919"), "US Open 2025, Herren-Doppel"),
            new OtherApiCompetition(UUID.fromString("32ed0f95-6dc0-4e79-8303-2ca7a1e36f99"), "US Open 2024, Herren-Doppel"),
            new OtherApiCompetition(UUID.fromString("f9dd0683-dd72-4d16-a319-87b3693705d3"), "US Open 2025, Damen-Doppel"),
            new OtherApiCompetition(UUID.fromString("9760d00d-6952-4cc2-9826-a3b865d58cfe"), "US Open 2024, Damen-Doppel"),
            new OtherApiCompetition(UUID.fromString("ecb96cfa-257c-425e-89f5-5b701de589b7"), "US Open 2025, Mixed"),
            new OtherApiCompetition(UUID.fromString("4486a4d6-89f6-47d9-aa78-4862e4ac065e"), "US Open 2024, Mixed")
        );

        competitionRepository.saveAll(competitions);

        final var players = List.of(
            new OtherApiPlayer(UUID.fromString("a1b2c3d4-e5f6-7890-abcd-ef1234567890"), "Jannik Sinner", LocalDate.of(2001, 8, 16), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("b1c2d3e4-f5a6-7890-abcd-ef1234567890"), "Nicolás Jarry", LocalDate.of(1996, 10, 11), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("c1d2e3f4-a5b6-7890-abcd-ef1234567890"), "Tristan Schoolkate", LocalDate.of(1999, 3, 7), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("d1e2f3a4-b5c6-7890-abcd-ef1234567890"), "Taro Daniel", LocalDate.of(1993, 1, 27), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("e1f2a3b4-c5d6-7890-abcd-ef1234567890"), "Marcos Giron", LocalDate.of(1993, 7, 24), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("f1a2b3c4-d5e6-7890-abcd-ef1234567890"), "Yannick Hanfmann", LocalDate.of(1992, 4, 16), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("a2b3c4d5-e6f7-8901-abcd-ef1234567890"), "Tomás Etcheverry", LocalDate.of(1999, 1, 18), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("fc203fbb-2d15-4ed2-bbb6-f934d729c615"), "Flavio Cobolli", LocalDate.of(2001, 2, 8), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("c2d3e4f5-a6b7-8901-abcd-ef1234567890"), "Hubert Hurkacz", LocalDate.of(1997, 2, 11), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("d2e3f4a5-b6c7-8901-abcd-ef1234567890"), "Tallon Griekspoor", LocalDate.of(1996, 7, 2), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("e2f3a4b5-c6d7-8901-abcd-ef1234567890"), "Miomir Kecmanovic", LocalDate.of(1999, 8, 31), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("f2a3b4c5-d6e7-8901-abcd-ef1234567890"), "Dušan Lajovic", LocalDate.of(1990, 6, 30), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("a3b4c5d6-e7f8-9012-abcd-ef1234567890"), "Matteo Berrettini", LocalDate.of(1996, 4, 12), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("b3c4d5e6-f7a8-9012-abcd-ef1234567890"), "Cameron Norrie", LocalDate.of(1995, 8, 23), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("c3d4e5f6-a8b9-0123-abcd-ef1234567890"), "Zhizhen Zhang", LocalDate.of(1997, 5, 16), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("d3e4f5a6-b8c9-0123-abcd-ef1234567890"), "Holger Rune", LocalDate.of(2003, 4, 29), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("e3f4a5b6-c8d9-0123-abcd-ef1234567890"), "Stefanos Tsitsipas", LocalDate.of(1998, 8, 12), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("f3a4b5c6-d8e9-0123-abcd-ef1234567890"), "Alex Michelsen", LocalDate.of(2002, 1, 1), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("a4b5c6d7-e8f9-1234-abcd-ef1234567890"), "James McCabe", LocalDate.of(2003, 2, 15), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("b4c5d6e7-f8a9-1234-abcd-ef1234567890"), "Martín Landaluce", LocalDate.of(2005, 3, 10), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("b5c6d7e8-f0a1-2345-abcd-ef1234567890"), "Alexander Bublik", LocalDate.of(1997, 6, 17), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("6d3a8b27-f2c1-4d59-93a7-8e4c9b5f12a6"), "Aryna Sabalenka", LocalDate.of(1998, 5, 5), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("c7f2d9e3-4a61-41b8-93b6-85a2e7d4c1f9"), "Sloane Stephens", LocalDate.of(1993, 3, 20), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("a52c7d8b-6f43-49e1-82a7-3f9b4c5a1d86"), "Naomi Osaka", LocalDate.of(1997, 10, 16), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("d9e5a72c-4b3f-48d1-81a6-7c5b2f94a8c3"), "Caroline Garcia", LocalDate.of(1993, 10, 16), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("f7c9a32d-5b46-4e8a-91c3-82d6b4a75f21"), "Coco Gauff", LocalDate.of(2004, 3, 13), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("b48c7a62-9d5f-41e3-72a8-6f5d2c93b7a1"), "Sofia Kenin", LocalDate.of(1998, 11, 14), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("e7d3c9b2-4f5a-41a6-82c1-9b74a8d5f62c"), "Iga Swiatek", LocalDate.of(2001, 5, 31), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("3d2f8b7c-5a49-42e6-91a3-72c5d4b79a8f"), "Katerina Siniakova", LocalDate.of(1996, 5, 10), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("a9c5d7b4-3f82-41e1-96a8-2d5f7c93b4a7"), "Linda Noskova", LocalDate.of(2004, 11, 17), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("d8c7b52f-41a3-42e6-91a9-7c5d4f32b8a7"), "Clara Tauson", LocalDate.of(2002, 12, 21), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("f3b7c92d-5a49-41e1-86a8-72d5c4b79a8f"), "Jelena Ostapenko", LocalDate.of(1997, 6, 8), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("7c5d92b3-4a61-42e9-81a3-6d5f4c78b2a7"), "Belinda Bencic", LocalDate.of(1997, 3, 10), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("b5d8c72f-4a3c-41e1-96a7-2d7f5c94b3a8"), "Sorana Cirstea", LocalDate.of(1990, 4, 7), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("e2c7b9d3-5f41-41a6-82a1-7c5d4b78f92a"), "Elina Svitolina", LocalDate.of(1994, 9, 12), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("a7d5c92b-3f4a-41e1-86a8-72c9d4b57a8f"), "Emma Raducanu", LocalDate.of(2002, 11, 13), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("d8b7c92f-5a41-42e6-91a3-6c5d4f79b3a8"), "Ekaterina Alexandrova", LocalDate.of(1994, 11, 15), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("f2c7b3d9-4a61-41e1-86a8-72d5c4b79a8f"), "Emma Navarro", LocalDate.of(2001, 5, 18), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("b7a7c06e-770e-4b7c-99e1-c52f00471f23"), "Peyton Stearns", LocalDate.of(2001, 10, 8), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("8c60964e-ed06-457a-9885-2f6b06ade427"), "Ons Jabeur", LocalDate.of(1994, 8, 28), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("735942db-689e-422a-9fb9-e7a3996e3bbf"), "Anhelina Kalinina", LocalDate.of(1997, 2, 7), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("4bc46be5-4751-4036-8d8c-cda614203d9a"), "Marcelo Arévalo", LocalDate.of(1990, 10, 17), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("2a5816e9-f3ea-4aa1-8601-b621b1b28f0e"), "Mate Pavic", LocalDate.of(1993, 7, 4), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("251e0f8c-b087-48ab-a58c-8575eacc2c2a"), "Jason Kubler", LocalDate.of(1993, 5, 19), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("c7031f6f-5f8b-45e2-8569-f70ece81e4e2"), "Rinky Hijikata", LocalDate.of(2001, 2, 23), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("dff04213-d7c5-4ad9-b4dd-fce3f68a5e01"), "Simone Bolelli", LocalDate.of(1985, 10, 8), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("5e9e1479-8146-4623-9ef7-b69e749a13e8"), "Andrea Vavassori", LocalDate.of(1995, 5, 5), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("5a9020f8-bbd8-44c7-bc77-6f3710e710ac"), "Damir Dzumhur", LocalDate.of(1992, 5, 20), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("d7811e22-8954-4349-b2e0-28e3c7553829"), "Petros Tsitsipas", LocalDate.of(2000, 7, 27), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("ddd60010-b1db-4c3b-8e61-48cd1e291a67"), "Kamil Drzewiecki", LocalDate.of(1996, 10, 5), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("75348e7d-9dbf-4eb6-8d51-876773282870"), "Abhishek Chandrasekar", LocalDate.of(1998, 3, 15), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("b5d63d6a-8698-442b-9a73-70fd1317effe"), "Pablo Carreno Busta", LocalDate.of(1991, 7, 12), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("5257139f-cc28-461a-aefb-7344b36d7b0b"), "Sergio Martos Gornes", LocalDate.of(1993, 1, 20), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("5916b3ff-0337-4f6b-a929-c1e76c5395dd"), "Sander Arends", LocalDate.of(1991, 8, 9), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("1ac303a0-5d4a-4b2b-a660-6061c21792dd"), "Luke Johnson", LocalDate.of(1994, 1, 7), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("13f3e9f1-ab43-43a0-8cd7-dd37e37ba4aa"), "Petr Nouza", LocalDate.of(1998, 7, 2), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("a5184a72-154c-407a-a452-8dd955bc912d"), "Patrik Rikl", LocalDate.of(1999, 12, 18), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("f57e35fb-3b7a-4361-a483-8cea78906c59"), "Sadio Doumbia", LocalDate.of(1991, 4, 15), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("6837074a-6a62-4875-9d76-9615860f72d4"), "Florian Reboul", LocalDate.of(1995, 6, 23), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("3678498b-76ba-436e-81a0-e2397fc51086"), "Joe Salisbury", LocalDate.of(1992, 4, 20), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("f1a2b6c3-b945-493f-bcd0-89452d12b16a"), "Neal Skupski", LocalDate.of(1989, 12, 1), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("3417a0a0-775b-44a8-8028-eb93bf0e32bf"), "Sebastian Baez", LocalDate.of(2000, 12, 28), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("3ea5c9b1-ef45-49bc-8c63-f26e569805d2"), "Francisco Comesana", LocalDate.of(1999, 11, 15), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("c916a937-ea74-4c50-b5f8-60ec739ae031"), "Andreas Goransson", LocalDate.of(1993, 7, 8), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("4312199d-22c7-4cd9-9219-37e6be8fb17e"), "Sander Verbeek", LocalDate.of(1998, 9, 10), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("b2c3d4e5-f6a7-8901-abcd-ef1234567890"), "Alexander Erler", LocalDate.of(1996, 8, 16), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("f97cd4fc-f862-49e3-b75e-3f17e61d9131"), "Andreas Mies", LocalDate.of(1990, 11, 21), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("562e95fc-4520-4fb4-92be-f8ca1c3f7362"), "James Duckworth", LocalDate.of(1992, 1, 21), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("66acddec-72cf-4e4d-a652-30c501d2d0a8"), "Alexander Vukic", LocalDate.of(1996, 4, 6), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("476a5ecf-a676-4d42-b836-24ec9a1d3c29"), "Thanasi Kokkinakis", LocalDate.of(1996, 4, 10), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("cb49af8d-33c0-4339-9785-507931688a47"), "Nick Kyrgios", LocalDate.of(1995, 4, 27), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("359bd528-e22d-4ef9-a036-93fd11a58d33"), "Luke Saville", LocalDate.of(1994, 2, 1), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("402bdd6e-ea96-4147-b1c1-4263bcea0f36"), "Li Tu", LocalDate.of(1998, 1, 16), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("091d78cc-87d7-4505-83e4-02cf234d7afa"), "Maximo Gonzalez", LocalDate.of(1983, 11, 30), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("600480d6-bc0b-4cac-bf37-b99fadfdd293"), "Andres Molteni", LocalDate.of(1987, 1, 20), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("4cb536b4-d32a-4cc6-a46b-0a86b2adf5cb"), "Hendrik Jebens", LocalDate.of(1995, 6, 15), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("1d2804b1-984e-4d36-96b7-a82925ed0c36"), "Cornelius Frantzen", LocalDate.of(1998, 3, 22), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("56663105-e1b4-41b3-bb6c-7836ea77a656"), "Marcelo Bortolotti", LocalDate.of(1991, 2, 3), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("673707cc-89e5-4aa3-b944-4454f1d6a8f8"), "Daniel Hidalgo", LocalDate.of(1995, 6, 15), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("d96789ea-1fa6-4fae-a606-22c0eb937e61"), "Luciano Darderi", LocalDate.of(2001, 2, 16), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("9faf8fb8-18dc-4463-96e6-ddf94a30e046"), "Adam Pavlásek", LocalDate.of(1994, 1, 15), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("7a1d86df-6a06-484f-9fec-3d5674e712be"), "Jean-Julien Rojer", LocalDate.of(1981, 8, 25), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("b1c2d3e4-f5a6-7890-abcd-ef1234567891"), "Romain Arneodo", LocalDate.of(1990, 7, 8), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("a5b045eb-e1c5-4655-8237-f4757ca13ba9"), "Arthur Cazaux", LocalDate.of(2002, 3, 1), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("f4df8a91-bc12-4bbd-b36f-00d84dbadd47"), "Taylor Townsend", LocalDate.of(1996, 4, 16), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("033b0e95-8498-481b-ab15-f5491e2be421"), "Lulu Sun", LocalDate.of(2001, 4, 14), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("b5e2a870-657a-4bfd-b864-22301a78d486"), "Veronika Kudermetova", LocalDate.of(1997, 4, 24), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("48aff582-5395-477e-9f55-467ee3237e9e"), "Elise Mertens", LocalDate.of(1995, 11, 17), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("f650ea74-16b0-4975-8ce7-12e92066a193"), "Gabriela Dabrowski", LocalDate.of(1992, 3, 1), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("225abdff-6f79-42a0-98df-7b056ca152a9"), "Erin Routliffe", LocalDate.of(1996, 1, 10), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("de8165e5-b40f-42b4-a194-2140561973ea"), "Luisa Stefani", LocalDate.of(1997, 8, 9), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("1c739196-7040-4bf2-9db9-f612ed52d69a"), "Irina Begu", LocalDate.of(1990, 8, 26), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("1249a9f4-3cdb-4a10-8e79-862327ffa24c"), "Inga Gamarra Martins", LocalDate.of(1998, 6, 15), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("53112c9d-2346-4930-b8b3-50c7a5040078"), "Alycia Parks", LocalDate.of(2001, 1, 31), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("787ea792-6589-4b2c-914b-dbb1ce2a07ba"), "Qianhui Tang", LocalDate.of(2000, 1, 3), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("51fa6f26-1c87-4f64-af5e-466d41f0c571"), "Yafan Wang", LocalDate.of(1994, 1, 8), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("a560047f-645a-4b49-8359-eba515e561b9"), "Shuai Zheng", LocalDate.of(1994, 1, 21), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("06f74090-28de-4e12-8ad8-4570fe61abba"), "Sara Errani", LocalDate.of(1987, 4, 29), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("5a447ce4-7d67-4b68-a75f-a60912e249b5"), "Jasmine Paolini", LocalDate.of(1996, 1, 4), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("50a64c7d-13ea-43ea-b1ed-b415ec046deb"), "Priscilla Hon", LocalDate.of(1998, 1, 10), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("8c493fd7-77fc-4f3a-9bf3-9e1272cd5f35"), "Daria Saville", LocalDate.of(1994, 3, 5), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("c1726a55-37f5-410c-87f7-9a831883f214"), "Marta Kostyuk", LocalDate.of(2002, 6, 28), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("54149c91-4134-4f8d-b49e-609834d9fee0"), "Elena Gabriela Ruse", LocalDate.of(1997, 11, 6), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("5ee4b27d-cbba-447a-b0b9-eb6fd44a92b3"), "Destanee Aiava", LocalDate.of(1996, 1, 10), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("ad1f6109-e0b5-4548-ad71-3fa96f48b962"), "Maddison Inglis", LocalDate.of(1997, 1, 14), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("ff00ba43-2985-4213-bf25-51a4f81cff57"), "Laura Siegemund", LocalDate.of(1988, 3, 4), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("c4bf6fb6-6826-4891-a7bb-70411de64b18"), "Beatriz Haddad Maia", LocalDate.of(1996, 5, 30), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("953ffea8-99aa-4c1c-af57-10b459ebb07f"), "Quinn Gleason", LocalDate.of(1994, 9, 30), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("2ea8e67d-d353-4dc8-9037-50d4a9e64aea"), "Suzan Lamens", LocalDate.of(1995, 6, 15), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("edce8704-1a5f-4bbb-b2c1-80f9c05fb293"), "Miyu Kato", LocalDate.of(1995, 11, 2), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("568f1c2f-62c1-4022-a151-6cd5bf8cdcae"), "Renata Zarazua", LocalDate.of(1997, 1, 6), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("9dbc1f35-ecc4-465c-9d20-f7cb4f573c59"), "Monica Niculescu", LocalDate.of(1988, 9, 25), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("04594330-2b1c-42b1-915e-b416258d841b"), "Kristina Mladenovic", LocalDate.of(1993, 5, 14), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("0c38defc-93ac-448b-b197-efadcb2ff8a4"), "Shuai Zhang", LocalDate.of(1989, 1, 21), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("22bafecf-f798-4a43-b64e-bd87e43d9213"), "Arantxa Rus", LocalDate.of(1991, 12, 13), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("fbec6de7-dc27-4d7d-8c2d-d972eda234da"), "McCartney Kessler", LocalDate.of(2003, 6, 15), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("c96c2bb4-2e49-49dd-95f3-19611d9cbcaf"), "Varvara Gracheva", LocalDate.of(2001, 3, 2), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("7d591f92-97d3-4aed-980e-55b83ceda631"), "Oxana Kalashnikova", LocalDate.of(1990, 1, 1), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("6a4a795e-afeb-40d2-b3b6-29ca0b8ec77b"), "Maia Lumsden", LocalDate.of(1997, 1, 1), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("78d981e1-4eb1-4fd1-8eb5-16b7b0a5cea7"), "Anna Siskova", LocalDate.of(1998, 7, 15), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("ace4c736-f827-4c9e-82bc-2c82e81eeff8"), "Ellen Perez", LocalDate.of(1995, 1, 10), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("58869e13-abf7-4ea7-a10c-69f04095b11d"), "Kevin Krawietz", LocalDate.of(1991, 11, 24), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("e9c3e6c7-9c54-4e19-8814-ed7744c20fc2"), "Hugo Nys", LocalDate.of(1993, 7, 15), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("341e77ff-0c7d-4ebd-be29-9c2866f30d19"), "Desirae Krawczyk", LocalDate.of(1993, 1, 11), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("f6272e7f-b996-4fe2-980e-e2d9b0a28610"), "Hsieh Su-wei", LocalDate.of(1986, 1, 4), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("681225ed-9855-4845-9832-cceae7816a8a"), "Jan Zielinski", LocalDate.of(1996, 1, 1), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("032fb4b6-6a02-415d-a589-7aee497aa97f"), "Demi Schuurs", LocalDate.of(1993, 11, 17), OtherApiGender.FEMALE),
            new OtherApiPlayer(UUID.fromString("6e1edc9c-56cb-4f15-8d96-e61d3b5ce7b7"), "Tim Pütz", LocalDate.of(1988, 7, 21), OtherApiGender.MALE),
            new OtherApiPlayer(UUID.fromString("a577aca8-3af6-453d-a579-329a12c60e76"), "Fanny Stollár", LocalDate.of(1998, 11, 12), OtherApiGender.FEMALE)
        );

        playerRepository.saveAll(players);

        final var australianOpen2025Men = competitionRepository.findById(UUID.fromString("5922141e-56ad-4318-bf77-0f373dfd839d")).get();
        final var australianOpen2025Women = competitionRepository.findById(UUID.fromString("34a76c5e-c91d-4e47-82a1-8c974c436745")).get();
        final var australianOpen2025MenDoubles = competitionRepository.findById(UUID.fromString("c4637c0d-4cd5-41ef-afc1-ac12eb788f44")).get();
        final var australianOpen2025WomenDoubles = competitionRepository.findById(UUID.fromString("59286657-7be8-489d-b160-484fecc319c5")).get();
        final var australianOpen2025Mixed = competitionRepository.findById(UUID.fromString("aa0f3a19-163f-4024-8a8e-fd29f75d744a")).get();


        final var helper = new OtherApiTestMatchHelper(playerRepository);

        final var matches = List.of(
            // Australian Open 2025, Herren
            helper.singeMatch("e802dd0b-42f4-4378-b0ee-f3d963daedd3", "Jannik Sinner vs Nicolás Jarry", "2025-01-13", "Jannik Sinner", "Nicolás Jarry", australianOpen2025Men),
            helper.singeMatch("bc0c65bc-71e5-482e-975b-dddf49f2051d", "Tristan Schoolkate vs Taro Daniel", "2025-01-13", "Tristan Schoolkate", "Taro Daniel", australianOpen2025Men),
            helper.singeMatch("f3a52bd7-1d44-41ee-814d-f93f2c42a5ee", "Marcos Giron vs. Yannick Hanfmann", "2025-01-13", "Marcos Giron", "Yannick Hanfmann", australianOpen2025Men),
            helper.singeMatch("a624ad7a-8f2e-4d02-85e9-5373fa5a54c8", "Tomás Etcheverry vs. Flavio Cobolli", "2025-01-13", "Tomás Etcheverry", "Flavio Cobolli", australianOpen2025Men),
            helper.singeMatch("67c9c7c9-d450-4456-a186-bcb6a4cda6a5", "Hubert Hurkacz vs. Tallon Griekspoor", "2025-01-13", "Hubert Hurkacz", "Tallon Griekspoor", australianOpen2025Men),
            helper.singeMatch("09de254d-fc32-45b0-9d45-8076561ddcf9", "Miomir Kecmanovic vs. Dušan Lajovic", "2025-01-13", "Miomir Kecmanovic", "Dušan Lajovic", australianOpen2025Men),
            helper.singeMatch("d98b9ee6-2e9c-45c8-bb9c-f23e5ecdf90c", "Matteo Berrettini vs. Cameron Norrie", "2025-01-13", "Matteo Berrettini", "Cameron Norrie", australianOpen2025Men),
            helper.singeMatch("1d6ddac3-4993-4cb0-baf7-240d74ad4b2c", "Zhizhen Zhang vs. Holger Rune", "2025-01-13", "Zhizhen Zhang", "Holger Rune", australianOpen2025Men),
            helper.singeMatch("b6f3b3ff-0fc1-44a7-a4b9-8fae4cf098fd", "Stefanos Tsitsipas vs. Alex Michelsen", "2025-01-13", "Stefanos Tsitsipas", "Alex Michelsen", australianOpen2025Men),
            helper.singeMatch("e9b8c9b3-7798-43f5-b9df-a29cbe96a1d8", "James McCabe vs. Martín Landaluce", "2025-01-13", "James McCabe", "Martín Landaluce", australianOpen2025Men),

            // Australian Open 2025, Damen
            helper.singeMatch("c4d3f71e-8816-41b7-8b34-9c841edf8c24", "Aryna Sabalenka vs. Sloane Stephens", "2025-01-13", "Aryna Sabalenka", "Sloane Stephens", australianOpen2025Women),
            helper.singeMatch("e916d6a7-6a42-4479-bc50-34c23fdc5db3", "Naomi Osaka vs. Caroline Garcia", "2025-01-13", "Naomi Osaka", "Caroline Garcia", australianOpen2025Women),
            helper.singeMatch("ab21e04c-bcb3-4883-a012-5af464f3edff", "Coco Gauff vs. Sofia Kenin", "2025-01-13", "Coco Gauff", "Sofia Kenin", australianOpen2025Women),
            helper.singeMatch("f35c70cc-fb98-40a9-bf56-2c03889ee2ee", "Iga Swiatek vs. Katerina Siniakova", "2025-01-13", "Iga Swiatek", "Katerina Siniakova", australianOpen2025Women),
            helper.singeMatch("5d7e2afc-b814-45aa-902e-83789f10a568", "Linda Noskova vs. Clara Tauson", "2025-01-13", "Linda Noskova", "Clara Tauson", australianOpen2025Women),
            helper.singeMatch("287f7a57-7b7e-472c-9aef-8c36ff8af9ad", "Jelena Ostapenko vs. Belinda Bencic", "2025-01-13", "Jelena Ostapenko", "Belinda Bencic", australianOpen2025Women),
            helper.singeMatch("ad97e31b-cf4d-4ddc-a739-530efc7cf920", "Sorana Cirstea vs. Elina Svitolina", "2025-01-13", "Sorana Cirstea", "Elina Svitolina", australianOpen2025Women),
            helper.singeMatch("b462cb18-2168-4724-beb1-4ad6b929f643", "Emma Raducanu vs. Ekaterina Alexandrova", "2025-01-13", "Emma Raducanu", "Ekaterina Alexandrova", australianOpen2025Women),
            helper.singeMatch("51b4bf87-0c42-4ee1-82d2-c0dc51b586b6", "Emma Navarro vs. Peyton Stearns", "2025-01-13", "Emma Navarro", "Peyton Stearns", australianOpen2025Women),
            helper.singeMatch("9d86cf5b-b174-4d84-9933-72cb3cbe06cf", "Ons Jabeur vs. Anhelina Kalinina", "2025-01-13", "Ons Jabeur", "Anhelina Kalinina", australianOpen2025Women),

            // Australian Open 2025, Herren-Doppel
            helper.doubleMatch("37b6c129-5b4a-4c48-a7ff-8ef2d6cf7c1d", "Marcelo Arévalo/Mate Pavic vs. Jason Kubler/Rinky Hijikata", "2025-01-16", "Marcelo Arévalo", "Mate Pavic", "Jason Kubler", "Rinky Hijikata", australianOpen2025MenDoubles),
            helper.doubleMatch("2e849dae-a17d-4e26-b251-1e543cfa0aa4", "Damir Dzumhur/Petros Tsitsipas vs. Kamil Drzewiecki/Abhishek Chandrasekar", "2025-01-16", "Damir Dzumhur", "Petros Tsitsipas", "Kamil Drzewiecki", "Abhishek Chandrasekar", australianOpen2025MenDoubles),
            helper.doubleMatch("bf03f394-5ed8-4183-9c5e-68b37dbb6ba4", "Pablo Carreno Busta/Sergio Martos Gornes vs. Sander Arends/Luke Johnson", "2025-01-16", "Pablo Carreno Busta", "Sergio Martos Gornes", "Sander Arends", "Luke Johnson", australianOpen2025MenDoubles),
            helper.doubleMatch("72c8f763-5f7e-4455-98a8-bb2d394c303e", "Petr Nouza/Patrik Rikl vs. Sadio Doumbia/Florian Reboul", "2025-01-16", "Petr Nouza", "Patrik Rikl", "Sadio Doumbia", "Florian Reboul", australianOpen2025MenDoubles),
            helper.doubleMatch("a9e6942c-4dd4-4f0b-9119-e554c13e4a64", "Joe Salisbury/Neal Skupski vs. Sebastian Baez/Francisco Comesana", "2025-01-16", "Joe Salisbury", "Neal Skupski", "Sebastian Baez", "Francisco Comesana", australianOpen2025MenDoubles),
            helper.doubleMatch("c5827f42-6615-4d82-bf92-183a2565a6ab", "Andreas Goransson/Sander Verbeek vs. Alexander Erler/Andreas Mies", "2025-01-16", "Andreas Goransson", "Sander Verbeek", "Alexander Erler", "Andreas Mies", australianOpen2025MenDoubles),
            helper.doubleMatch("e14b6b8f-8a8f-4d02-af3b-348fc8a55489", "James Duckworth/Alexander Vukic vs. Thanasi Kokkinakis/Nick Kyrgios", "2025-01-16", "James Duckworth", "Alexander Vukic", "Thanasi Kokkinakis", "Nick Kyrgios", australianOpen2025MenDoubles),
            helper.doubleMatch("15a4f9c8-5d2b-4c03-8f4c-92b6b3d46e86", "Luke Saville/Li Tu vs. Maximo Gonzalez/Andres Molteni", "2025-01-16", "Luke Saville", "Li Tu", "Maximo Gonzalez", "Andres Molteni", australianOpen2025MenDoubles),
            helper.doubleMatch("d7e8fb29-bc27-4881-86b4-d8fc21dcb6cd", "Simone Bolelli/Andrea Vavassori vs. Hendrik Jebens/Cornelius Frantzen", "2025-01-16", "Simone Bolelli", "Andrea Vavassori", "Hendrik Jebens", "Cornelius Frantzen", australianOpen2025MenDoubles),
            helper.doubleMatch("4682d71f-9af5-47ab-99fa-4d88287e9cfb", "Marcelo Bortolotti/Flavio Cobolli vs. Daniel Hidalgo/Luciano Darderi", "2025-01-16", "Marcelo Bortolotti", "Flavio Cobolli", "Daniel Hidalgo", "Luciano Darderi", australianOpen2025MenDoubles),
            helper.doubleMatch("946086c8-49ae-478a-a0fb-17162290fb40", "Adam Pavlásek/Jean-Julien Rojer vs. Romain Arneodo/Arthur Cazaux", "2025-01-16", "Adam Pavlásek", "Jean-Julien Rojer", "Romain Arneodo", "Arthur Cazaux", australianOpen2025MenDoubles),

            // Australian Open 2025, Damen-Doppel
            helper.doubleMatch("6f2d8b13-91d1-45d6-b9e4-a9a0c5a9f2a3", "Katerina Siniakova/Taylor Townsend vs. Fanny Stollár/Lulu Sun", "2025-01-15", "Katerina Siniakova", "Taylor Townsend", "Fanny Stollár", "Lulu Sun", australianOpen2025WomenDoubles),
            helper.doubleMatch("b18d9f79-09d6-4cda-a712-5f4edc176e24", "Veronika Kudermetova/Elise Mertens vs. Gabriela Dabrowski/Erin Routliffe", "2025-01-16", "Veronika Kudermetova", "Elise Mertens", "Gabriela Dabrowski", "Erin Routliffe", australianOpen2025WomenDoubles),
            helper.doubleMatch("c20b3f02-7683-4373-b70a-45a0e1db3b6f", "Luisa Stefani/Peyton Stearns vs. Irina Begu/Inga Gamarra Martins", "2025-01-16", "Luisa Stefani", "Peyton Stearns", "Irina Begu", "Inga Gamarra Martins", australianOpen2025WomenDoubles),
            helper.doubleMatch("af9e27dc-5e2f-4691-92a6-278bd2b5f14a", "Alycia Parks/Qianhui Tang vs. Yafan Wang/Shuai Zheng", "2025-01-16", "Alycia Parks", "Qianhui Tang", "Yafan Wang", "Shuai Zheng", australianOpen2025WomenDoubles),
            helper.doubleMatch("d7bc3a62-67f8-4da4-9884-0e5f81792ff6", "Sara Errani/Jasmine Paolini vs. Priscilla Hon/Daria Saville", "2025-01-16", "Sara Errani", "Jasmine Paolini", "Priscilla Hon", "Daria Saville", australianOpen2025WomenDoubles),
            helper.doubleMatch("68a3eb5d-f2b4-4a5d-b784-9e7f842b9273", "Marta Kostyuk/Elena Gabriela Ruse vs. Destanee Aiava/Maddison Inglis", "2025-01-16", "Marta Kostyuk", "Elena Gabriela Ruse", "Destanee Aiava", "Maddison Inglis", australianOpen2025WomenDoubles),
            helper.doubleMatch("c9e245bd-61a7-4e9d-bd24-55a7e2b4fe5a", "Laura Siegemund/Beatriz Haddad Maia vs. Quinn Gleason/Suzan Lamens", "2025-01-16", "Laura Siegemund", "Beatriz Haddad Maia", "Quinn Gleason", "Suzan Lamens", australianOpen2025WomenDoubles),
            helper.doubleMatch("7fe2a49d-3f06-4d9c-b8b5-65a9e272a4b3", "Miyu Kato/Renata Zarazua vs. Sofia Kenin/Monica Niculescu", "2025-01-16", "Miyu Kato", "Renata Zarazua", "Sofia Kenin", "Monica Niculescu", australianOpen2025WomenDoubles),
            helper.doubleMatch("8f6b4e3a-25d0-4c2b-9815-b6a7c92d3725", "Kristina Mladenovic/Shuai Zhang vs. McCartney Kessler/Arantxa Rus", "2025-01-16", "Kristina Mladenovic", "Shuai Zhang", "McCartney Kessler", "Arantxa Rus", australianOpen2025WomenDoubles),
            helper.doubleMatch("a92db374-8e0d-4993-86a5-7f5b62c9a8d4", "Varvara Gracheva/Oxana Kalashnikova vs. Maia Lumsden/Anna Siskova", "2025-01-16", "Varvara Gracheva", "Oxana Kalashnikova", "Maia Lumsden", "Anna Siskova", australianOpen2025WomenDoubles),

            // Australian Open 2025, Mixed
            helper.doubleMatch("3a4d6f9b-7e1c-4db6-84f2-a7b1c53a2d89", "Sara Errani/Andrea Vavassori vs. Ellen Perez/Kevin Krawietz", "2025-01-16", "Sara Errani", "Andrea Vavassori", "Ellen Perez", "Kevin Krawietz", australianOpen2025Mixed),
            helper.doubleMatch("bf91e72d-3c8a-4e6d-b84b-5f6a2e8c1d47", "Taylor Townsend/Hugo Nys vs. Desirae Krawczyk/Neal Skupski", "2025-01-16", "Taylor Townsend", "Hugo Nys", "Desirae Krawczyk", "Neal Skupski", australianOpen2025Mixed),
            helper.doubleMatch("6d3c8e91-bf27-4b5e-a72c-8f4b6d9a1c35", "Hsieh Su-wei/Jan Zielinski vs. Demi Schuurs/Tim Pütz", "2025-01-16", "Hsieh Su-wei", "Jan Zielinski", "Demi Schuurs", "Tim Pütz", australianOpen2025Mixed)
        );

        matchRepository.saveAll(matches);
    }



    class OtherApiTestMatchHelper {
        private OtherApiPlayerRepository playerRepository;

        public OtherApiTestMatchHelper(OtherApiPlayerRepository playerRepository) {
            this.playerRepository = playerRepository;
        }

        OtherApiMatch singeMatch(String id, String name, String matchDate, String playerOneName, String playerTwoName, OtherApiCompetition competition) {
            final var idParsed = UUID.fromString(id);
            final var matchDateParsed = LocalDate.parse(matchDate);
            final var teamOneFirstPlayer = playerRepository.findByName(playerOneName).orElseThrow(() -> new IllegalArgumentException("Player not found: " + playerOneName));
            final var teamTwoFirstPlayer = playerRepository.findByName(playerTwoName).orElseThrow(() -> new IllegalArgumentException("Player not found: " + playerTwoName));
            return OtherApiMatch.ofSingle(idParsed, name, matchDateParsed, teamOneFirstPlayer, teamTwoFirstPlayer, competition);
        }

        OtherApiMatch doubleMatch(String id, String name, String matchDate, String teamOnePlayerOneName, String teamOnePlayerTwoName, String teamTwoPlayerOneName, String teamTwoPlayerTwoName, OtherApiCompetition competition) {
            final var idParsed = UUID.fromString(id);
            final var matchDateParsed = LocalDate.parse(matchDate);
            final var teamOneFirstPlayer = playerRepository.findByName(teamOnePlayerOneName).orElseThrow(() -> new IllegalArgumentException("Player not found: " + teamOnePlayerOneName));
            final var teamOneSecondPlayer = playerRepository.findByName(teamOnePlayerTwoName).orElseThrow(() -> new IllegalArgumentException("Player not found: " + teamOnePlayerTwoName));
            final var teamTwoFirstPlayer = playerRepository.findByName(teamTwoPlayerOneName).orElseThrow(() -> new IllegalArgumentException("Player not found: " + teamTwoPlayerOneName));
            final var teamTwoSecondPlayer = playerRepository.findByName(teamTwoPlayerTwoName).orElseThrow(() -> new IllegalArgumentException("Player not found: " + teamTwoPlayerTwoName));

            return OtherApiMatch.ofDouble(idParsed, name, matchDateParsed, teamOneFirstPlayer, teamOneSecondPlayer, teamTwoFirstPlayer, teamTwoSecondPlayer, competition);
        }
    }
}
