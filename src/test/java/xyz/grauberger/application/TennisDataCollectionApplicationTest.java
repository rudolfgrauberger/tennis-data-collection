package xyz.grauberger.application;

import xyz.grauberger.application.idmapping.IdMappingTestDataTestConfiguration;
import xyz.grauberger.application.collection.CollectionTestDataTestConfiguration;
import xyz.grauberger.application.fdi.FdiTestDataTestConfiguration;
import xyz.grauberger.application.masterdata.MasterdataTestDataConfiguration;

import org.springframework.boot.SpringApplication;

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
}
