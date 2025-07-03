package xyz.grauberger.application.masterdata.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import xyz.grauberger.application.masterdata.dtos.tournament.TournamentDetailDto;
import xyz.grauberger.application.masterdata.dtos.tournament.TournamentListDto;
import xyz.grauberger.application.masterdata.dtos.tournament.TournamentInputDto;
import xyz.grauberger.application.masterdata.entities.Location;
import xyz.grauberger.application.masterdata.entities.tournament.Tournament;
import xyz.grauberger.application.masterdata.exceptions.TournamentNotFoundException;
import xyz.grauberger.application.masterdata.repositories.TournamentRepository;

@Service
@RequiredArgsConstructor
public class TournamentService {
    private final TournamentRepository tournamentRepository;

    @Transactional(readOnly = true)
    public List<TournamentListDto> getAllTournaments() {
        return tournamentRepository.findAll().stream()
            .map(TournamentService::mapToListDto)
            .toList();
    }

    @Transactional(readOnly = true)
    public TournamentDetailDto getTournamentDetailsById(long id) {
        final var tournament = tournamentRepository.findById(id)
            .orElseThrow(() -> new TournamentNotFoundException(id));

        return mapToDetailDto(tournament);
    }

    @Transactional
    public TournamentDetailDto createTournament(TournamentInputDto tournamentCreateDto) {
        final var location = new Location(
            tournamentCreateDto.isoCountry(),
            tournamentCreateDto.city()
        );

        final var tournament = new Tournament(
            tournamentCreateDto.name(),
            tournamentCreateDto.surface(),
            location,
            tournamentCreateDto.description()
        );

        final var savedTournament = tournamentRepository.save(tournament);

        return mapToDetailDto(savedTournament);
    }

    private static TournamentListDto mapToListDto(Tournament tournament) {
        return new TournamentListDto(
            tournament.getId(),
            tournament.getName(),
            "%s (%s)".formatted(tournament.getLocation().city(), tournament.getLocation().countryISO()),
            tournament.getSurface().name(),
            tournament.getDescription()
        );
    }

    private static TournamentDetailDto mapToDetailDto(Tournament tournament) {
        return new TournamentDetailDto(
            tournament.getId(),
            tournament.getName(),
            tournament.getSurface(),
            tournament.getLocation().city(),
            tournament.getLocation().countryISO(),
            tournament.getDescription()
        );
    }

    @Transactional
    public TournamentDetailDto updateTournament(Long id, TournamentInputDto tournament) {
        final var existingTournament = tournamentRepository.findById(id)
            .orElseThrow(() -> new TournamentNotFoundException(id));

        existingTournament.update(
            tournament.name(),
            tournament.surface(),
            new Location(tournament.city(), tournament.isoCountry()),
            tournament.description()
        );

        return mapToDetailDto(existingTournament);
    }

    @Transactional
    public void deleteTournament(Long id) {
        tournamentRepository.deleteById(id);
    }
}
