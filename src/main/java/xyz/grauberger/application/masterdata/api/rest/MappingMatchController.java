package xyz.grauberger.application.masterdata.api.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import xyz.grauberger.application.fdi.provider.DataProvider;
import xyz.grauberger.application.masterdata.dtos.mapping.MasterdataMatchDto;
import xyz.grauberger.application.masterdata.idmapping.Mapping;
import xyz.grauberger.application.masterdata.idmapping.ProviderMatch;
import xyz.grauberger.application.masterdata.services.MapService;
import xyz.grauberger.application.masterdata.services.MappingDataService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@Tag(name = "Matches", description = "Operations related to mapping matches between our system and external providers")
@RestController
@RequestMapping("/api/idmapping/matches")
public class MappingMatchController {
    private final MapService mappingService;
    private final MappingDataService mappingDataService;

    public MappingMatchController(MapService mappingService, MappingDataService mappingDataService) {
        this.mappingService = mappingService;
        this.mappingDataService = mappingDataService;
    }

    @Operation(summary = "Get all our matches")
    @GetMapping(produces = "application/json")
    public List<MasterdataMatchDto> getMatches() {
        return mappingDataService.getMatches();
    }

    @Operation(summary = "Get matches for a specific provider")
    @GetMapping(path = "/{provider}/matches", produces = "application/json")
    public List<ProviderMatch> getProviderCompetitions(@PathVariable DataProvider provider) {
        return mappingDataService.getMatchesByProvider(provider);
    }

    @Operation(summary = "Map a match ID to a provider's ID")
    @PutMapping(path = "/{id}/{provider}/matches/{providerId}", produces = "application/json", consumes = "application/json")
    public Mapping map(@PathVariable long id, @PathVariable DataProvider provider, @PathVariable String providerId) {
        return mappingService.mapMatch(id, provider, providerId);
    }
}
