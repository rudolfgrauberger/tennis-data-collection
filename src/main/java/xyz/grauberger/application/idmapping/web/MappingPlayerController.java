package xyz.grauberger.application.idmapping.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import xyz.grauberger.application.fdi.provider.DataProvider;
import xyz.grauberger.application.idmapping.core.Mapping;
import xyz.grauberger.application.idmapping.core.MappingDataService;
import xyz.grauberger.application.idmapping.core.MapService;
import xyz.grauberger.application.idmapping.core.ProviderPlayer;
import xyz.grauberger.application.idmapping.web.dto.MasterdataPlayerDto;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Tag(name = "Players", description = "Operations related to mapping players between our system and external providers")
@RestController
@RequestMapping("/api/idmapping/players")
public class MappingPlayerController {
    private final MapService mappingService;
    private final MappingDataService mappingDataService;

    public MappingPlayerController(MapService mappingService, MappingDataService mappingDataService) {
        this.mappingService = mappingService;
        this.mappingDataService = mappingDataService;
    }

    @Operation(summary = "Get all our players")
    @GetMapping(produces = "application/json")
    public List<MasterdataPlayerDto> getPlayers() {
        return mappingDataService.getPlayers();
    }

    @Operation(summary = "Get players for a specific provider")
    @GetMapping(path = "/{provider}/players", produces = "application/json")
    public List<ProviderPlayer> getProviderPlayers(@PathVariable DataProvider provider) {
        return mappingDataService.getPlayersByProvider(provider);
    }

    @Operation(summary = "Map a player ID to a provider's ID")
    @PutMapping(path = "/{id}/{provider}/players/{providerId}", produces = "application/json", consumes = "application/json")
    public Mapping map(@PathVariable long id, @PathVariable DataProvider provider, @PathVariable String providerId) {
        return mappingService.mapPlayer(id, provider, providerId);
    }
}
