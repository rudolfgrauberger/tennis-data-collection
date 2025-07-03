package xyz.grauberger.application.masterdata.api.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import xyz.grauberger.application.masterdata.dtos.player.PlayerDetailDto;
import xyz.grauberger.application.masterdata.dtos.player.PlayerListDto;
import xyz.grauberger.application.masterdata.dtos.player.PlayerInputDto;
import xyz.grauberger.application.masterdata.services.PlayerService;

@Tag(name = "Player Management", description = "APIs for managing players in the system.")
@RestController
@RequestMapping(value = "/api/v1/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping(produces = "application/json")
    public List<PlayerListDto> getPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public PlayerDetailDto getPlayerById(@PathVariable long id) {
        return playerService.getPlayerDetailsById(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public PlayerDetailDto createPlayer(@Valid @RequestBody PlayerInputDto playerCreateDto) {
        return playerService.createPlayer(playerCreateDto);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public PlayerDetailDto updatePlayer(@PathVariable Long id, @Valid @RequestBody PlayerInputDto playerUpdateDto) {
        return playerService.updatePlayer(id, playerUpdateDto);
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
    }
}
