package xyz.grauberger.application.masterdata.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MasterdataConfig {
    @Bean
    public GroupedOpenApi masterdata() {
        return GroupedOpenApi.builder()
                .group("Masterdata")
                .packagesToScan("xyz.grauberger.application.masterdata.api.rest")
                .build();
    }
}
