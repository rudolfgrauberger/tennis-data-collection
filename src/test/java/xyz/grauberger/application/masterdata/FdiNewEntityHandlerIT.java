package xyz.grauberger.application.masterdata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import xyz.grauberger.application.PostgresDBContainerDevMode;
import xyz.grauberger.application.fdi.FdiMatchService;
import xyz.grauberger.application.fdi.NewPlayerAvailable;
import xyz.grauberger.application.masterdata.entities.player.Gender;
import xyz.grauberger.application.masterdata.idmapping.provider.examplecom.ExampleComAdapter;
import xyz.grauberger.application.masterdata.idmapping.provider.otherapi.OtherApiAdapter;
import xyz.grauberger.application.masterdata.repositories.PlayerRepository;
import xyz.grauberger.application.masterdata.services.MapService;
import xyz.grauberger.application.masterdata.services.MappingDataService;

@Import(PostgresDBContainerDevMode.class)
@ApplicationModuleTest
class FdiNewEntityHandlerIT {

    @MockitoBean
    private ExampleComAdapter exampleComAdapter;

    @MockitoBean
    private OtherApiAdapter otherApiAdapter;

    @MockitoBean
    private MapService mapService;

    @MockitoBean
    private MappingDataService mappingDataService;

    @MockitoBean
    private FdiMatchService fdiMatchService;

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    void new_player_available_event_result_in_new_player_entity(Scenario scenario) {

        final var event = new NewPlayerAvailable("Max", "Mustermann", NewPlayerAvailable.Gender.MALE, LocalDate.of(1991, 1, 1));

        scenario.publish(event)
                .andWaitForStateChange(() -> playerRepository.findByName("Max Mustermann"))
                .andVerify(p -> {
                    final var player = p.get();
                    assertEquals("Max", player.getFirstName());
                    assertEquals("Mustermann", player.getLastName());
                    assertEquals(Gender.MEN, player.getGender());
                    assertEquals(LocalDate.of(1991, 1, 1), player.getDateOfBirth());
                });
    }
}
