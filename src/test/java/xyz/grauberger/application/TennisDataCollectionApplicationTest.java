package xyz.grauberger.application;

import xyz.grauberger.application.collection.CollectionTestDataTestConfiguration;
import xyz.grauberger.application.fdi.FdiTestDataTestConfiguration;
import xyz.grauberger.application.masterdata.IdMappingTestDataTestConfiguration;
import xyz.grauberger.application.masterdata.MasterdataTestDataConfiguration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

@SuppressWarnings("java:S2187") // This class is used for local development only
public class TennisDataCollectionApplicationTest {

    public static void main(String[] args) {
        SpringApplication.from(TennisDataCollectionApplication::main)
                .with(PostgresDBContainerDevMode.class)
                .with(MasterdataTestDataConfiguration.class)
                .with(FdiTestDataTestConfiguration.class)
                .with(IdMappingTestDataTestConfiguration.class)
                .with(CollectionTestDataTestConfiguration.class)
                .run(args);
    }

    @Test
    void verifyApplicationStructure() {
        ApplicationModules.of(TennisDataCollectionApplication.class)
            .verify();
    }

    @Test
    @SuppressWarnings("java:S2699")
    void writeDocumentation() {
        var modules = ApplicationModules.of(TennisDataCollectionApplication.class);

        new Documenter(modules)
                .writeDocumentation();
    }
}
