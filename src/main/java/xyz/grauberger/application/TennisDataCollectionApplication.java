package xyz.grauberger.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import xyz.grauberger.application.config.MainConfig;

@Import(MainConfig.class)
@SpringBootApplication
public class TennisDataCollectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(TennisDataCollectionApplication.class, args);
	}
}
