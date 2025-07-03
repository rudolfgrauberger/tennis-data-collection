package xyz.grauberger.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI internalApi() {
        return new OpenAPI()
            .info(new Info().title("Internal API")
                .contact(new Contact().name("Rudolf Grauberger"))
                .description("Internal API for the application")
                .version("1.0.0"));
    }
}
