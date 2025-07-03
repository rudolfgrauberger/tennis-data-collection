package xyz.grauberger.application.masterdata.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import xyz.grauberger.application.masterdata.dtos.player.PlayerDetailDto;
import xyz.grauberger.application.masterdata.dtos.player.PlayerListDto;
import xyz.grauberger.application.masterdata.dtos.player.PlayerInputDto;
import xyz.grauberger.application.masterdata.entities.player.Player;
import xyz.grauberger.application.masterdata.exceptions.PlayerNotFoundException;
import xyz.grauberger.application.masterdata.repositories.PlayerRepository;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Transactional(readOnly = true)
    public List<PlayerListDto> getAllPlayers() {
        return playerRepository.findAll().stream()
            .map(PlayerService::mapToListDto)
            .toList();
    }

    @Transactional(readOnly = true)
    public PlayerDetailDto getPlayerDetailsById(long id) {
        final var player = playerRepository.findById(id)
            .orElseThrow(() -> new PlayerNotFoundException(id));

        return mapToDetailDto(player);
    }

    @Transactional
    public PlayerDetailDto createPlayer(PlayerInputDto playerCreateDto) {
        final var player = new Player(
            playerCreateDto.firstName(),
            playerCreateDto.lastName(),
            playerCreateDto.gender(),
            playerCreateDto.birthDate()
        );

        final var savedPlayer = playerRepository.save(player);

        return mapToDetailDto(savedPlayer);
    }

    private static PlayerListDto mapToListDto(Player player) {
        return new PlayerListDto(
            player.getId(),
            player.getFirstName() + " " + player.getLastName(),
            player.getGender(),
            player.getDateOfBirth()
        );
    }

    private static PlayerDetailDto mapToDetailDto(Player player) {
        return new PlayerDetailDto(
            player.getId(),
            player.getFirstName(),
            player.getLastName(),
            player.getGender(),
            player.getDateOfBirth()
        );
    }

    @Transactional
    public PlayerDetailDto updatePlayer(long id, PlayerInputDto playerUpdateDto) {
        final var player = playerRepository.findById(id)
            .orElseThrow(() -> new PlayerNotFoundException(id));

        player.update(playerUpdateDto.firstName(), playerUpdateDto.lastName(), playerUpdateDto.gender(), playerUpdateDto.birthDate());

        return mapToDetailDto(player);
    }

    @Transactional
    public void deletePlayer(long id) {
        playerRepository.deleteById(id);
    }
}
