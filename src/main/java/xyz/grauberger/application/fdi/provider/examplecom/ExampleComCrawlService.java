package xyz.grauberger.application.fdi.provider.examplecom;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import xyz.grauberger.application.fdi.NewPlayerAvailable;

@Service
@RequiredArgsConstructor
public class ExampleComCrawlService {
    private final ApplicationEventPublisher eventPublisher;

    public void syncMasterdata() {
        // This method would contain the logic to sync master data from the example provider.
        // It could involve fetching data from the website, extracting & transforming it, and saving it to a database.
        // For now, this is just a placeholder.

        eventPublisher.publishEvent(new NewPlayerAvailable("Max", "Mustermann", NewPlayerAvailable.Gender.MALE, null));
    }
}
