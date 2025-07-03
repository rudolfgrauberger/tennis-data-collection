package xyz.grauberger.application.idmapping.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdMappingConfig {
    @Bean
    public GroupedOpenApi idmapping() {
        return GroupedOpenApi.builder()
                .group("IdMapping")
                .packagesToScan("xyz.grauberger.application.idmapping.web")
                .build();
    }
}
