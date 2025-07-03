package xyz.grauberger.application.collection.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CollectionConfig {
    @Bean
    public GroupedOpenApi collection() {
        return GroupedOpenApi.builder()
                .group("Collection")
                .packagesToScan("xyz.grauberger.application.collection.management", "xyz.grauberger.application.collection.tool.web")
                .build();
    }
}
