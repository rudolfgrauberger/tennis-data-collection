package xyz.grauberger.application.fdi.provider.examplecom;

import lombok.RequiredArgsConstructor;
import xyz.grauberger.application.masterdata.services.MatchService;
import xyz.grauberger.application.masterdata.services.PlayerService;

@RequiredArgsConstructor
public class ExampleComCrawlService {
    private final MatchService matchService;
    private final PlayerService playerService;

    public void syncMasterdata() {
        // This method would contain the logic to sync master data from the example provider.
        // It could involve fetching data from the website, extracting & transforming it, and saving it to a database.
        // For now, this is just a placeholder.
    }
}
