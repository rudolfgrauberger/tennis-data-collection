package xyz.grauberger.application.fdi.provider.examplecom;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;
import xyz.grauberger.application.PostgresDBContainerDevMode;
import xyz.grauberger.application.fdi.NewPlayerAvailable;

@Import(PostgresDBContainerDevMode.class)
@ApplicationModuleTest
class ExampleComCrawlServiceIT {

    @Autowired
    private ExampleComCrawlService exampleComCrawlService;

    @Test
    void newPlayerAvailableEventIsPublished(Scenario scenario) {

        scenario.stimulate(() -> exampleComCrawlService.syncMasterdata())
            .andWaitForEventOfType(NewPlayerAvailable.class)
            .toArriveAndVerify(event -> {
                Assertions.assertThat(event)
                    .hasFieldOrPropertyWithValue("firstName", "Max")
                    .hasFieldOrPropertyWithValue("lastName", "Mustermann")
                    .hasFieldOrPropertyWithValue("gender", NewPlayerAvailable.Gender.MALE)
                    .hasFieldOrPropertyWithValue("birthDate", null);
            });
    }
}
