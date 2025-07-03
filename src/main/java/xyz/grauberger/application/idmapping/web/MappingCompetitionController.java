package xyz.grauberger.application.idmapping.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import xyz.grauberger.application.fdi.provider.DataProvider;
import xyz.grauberger.application.idmapping.core.Mapping;
import xyz.grauberger.application.idmapping.core.MappingDataService;
import xyz.grauberger.application.idmapping.core.MapService;
import xyz.grauberger.application.idmapping.core.ProviderCompetition;
import xyz.grauberger.application.idmapping.web.dto.MasterdataCompetitionDto;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@Tag(name = "Competitions", description = "Operations related to mapping competitions between our system and external providers")
@RestController
@RequestMapping("/api/idmapping/competitions")
public class MappingCompetitionController {
    private final MapService mappingService;
    private final MappingDataService mappingDataService;

    public MappingCompetitionController(MapService mappingService, MappingDataService mappingDataService) {
        this.mappingService = mappingService;
        this.mappingDataService = mappingDataService;
    }

    @Operation(summary = "Get all our competitions")
    @GetMapping(produces = "application/json")
    public List<MasterdataCompetitionDto> getCompetitions() {
        return mappingDataService.getCompetitions();
    }

    @Operation(summary = "Get competitions for a specific provider")
    @GetMapping(path = "/{provider}/competitions", produces = "application/json")
    public List<ProviderCompetition> getProviderCompetitions(@PathVariable DataProvider provider) {
        return mappingDataService.getCompetitionsByProvider(provider);
    }

    @Operation(summary = "Map a competition ID to a provider's ID")
    @PutMapping(path = "/{id}/{provider}/competitions/{providerId}", produces = "application/json", consumes = "application/json")
    public Mapping map(@PathVariable long id, @PathVariable DataProvider provider, @PathVariable String providerId) {
        return mappingService.mapCompetition(id, provider, providerId);
    }
}
