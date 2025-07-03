package xyz.grauberger.application.masterdata.services;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import xyz.grauberger.application.masterdata.dtos.competition.CompetitionDetailDto;
import xyz.grauberger.application.masterdata.dtos.competition.CompetitionListDto;
import xyz.grauberger.application.masterdata.dtos.competition.CompetitionInputDto;
import xyz.grauberger.application.masterdata.entities.Location;
import xyz.grauberger.application.masterdata.entities.competition.Competition;
import xyz.grauberger.application.masterdata.entities.competition.PlayStyle;
import xyz.grauberger.application.masterdata.entities.tournament.Tournament;
import xyz.grauberger.application.masterdata.exceptions.CompetitionNotFoundException;
import xyz.grauberger.application.masterdata.exceptions.TournamentNotFoundException;
import xyz.grauberger.application.masterdata.repositories.CompetitionRepository;
import xyz.grauberger.application.masterdata.repositories.TournamentRepository;

@Service
@RequiredArgsConstructor
public class CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final TournamentRepository tournamentRepository;

    @Transactional(readOnly = true)
    public List<CompetitionListDto> getAllCompetitions() {
        return competitionRepository.findAll().stream()
            .map(CompetitionService::mapToListDto)
            .toList();
    }

    @Transactional(readOnly = true)
    public CompetitionDetailDto getCompetitionDetailsById(long id) {
        final var competition = competitionRepository.findById(id)
            .orElseThrow(() -> new CompetitionNotFoundException(id));

        return mapToDetailDto(competition);
    }

    @Transactional
    public CompetitionDetailDto createCompetition(CompetitionInputDto competitionCreateDto) {
        Tournament tournament = null;
        final var playStyle = new PlayStyle(competitionCreateDto.type(), competitionCreateDto.gender());


        Competition competition = null;

        if (Objects.nonNull(competitionCreateDto.tournamentId())) {
            tournament = tournamentRepository.findById(competitionCreateDto.tournamentId())
                .orElseThrow(() -> new TournamentNotFoundException(competitionCreateDto.tournamentId()));

            competition = new Competition(competitionCreateDto.name(),
                                tournament,
                                playStyle,
                                competitionCreateDto.description());
        } else {
            final var location = new Location(
                competitionCreateDto.isoCountry(),
                competitionCreateDto.city()
            );

            competition = new Competition(
                competitionCreateDto.name(),
                playStyle,
                competitionCreateDto.surface(),
                location,
                competitionCreateDto.description()
            );
        }

        final var savedCompetition = competitionRepository.save(competition);

        return mapToDetailDto(savedCompetition);
    }

    private static CompetitionListDto mapToListDto(Competition competition) {
        return new CompetitionListDto(
            competition.getId(),
            competition.getName(),
            competition.getPlayStyle().toString(),
            "%s (%s)".formatted(
                competition.getLocation().city(),
                competition.getLocation().countryISO()
            ),
            competition.getSurface().name(),
            competition.getTournament() != null ? competition.getTournament().getName() : null
        );
    }

    private static CompetitionDetailDto mapToDetailDto(Competition competition) {
        return new CompetitionDetailDto(
            competition.getId(),
            competition.getName(),
            competition.getPlayStyle().type(),
            competition.getPlayStyle().gender(),
            competition.getSurface(),
            competition.getLocation().city(),
            competition.getLocation().countryISO(),
            competition.getDescription()
        );
    }

    @Transactional
    public CompetitionDetailDto updateCompetition(long id, CompetitionInputDto competitionUpdateDto) {
        final var competition = competitionRepository.findById(id)
            .orElseThrow(() -> new CompetitionNotFoundException(id));

        if (Objects.nonNull(competitionUpdateDto.tournamentId())) {
            final var tournament = tournamentRepository.findById(competitionUpdateDto.tournamentId())
                .orElseThrow(() -> new TournamentNotFoundException(competitionUpdateDto.tournamentId()));

            competition.update(
                competitionUpdateDto.name(),
                new PlayStyle(competitionUpdateDto.type(), competitionUpdateDto.gender()),
                tournament,
                competitionUpdateDto.description());
        } else {
            final var location = new Location(
                competitionUpdateDto.isoCountry(),
                competitionUpdateDto.city()
            );

            competition.update(
                competitionUpdateDto.name(),
                new PlayStyle(competitionUpdateDto.type(), competitionUpdateDto.gender()),
                competitionUpdateDto.surface(),
                location,
                competitionUpdateDto.description()
            );
        }

        final var updatedCompetition = competitionRepository.save(competition);

        return mapToDetailDto(updatedCompetition);
    }

    @Transactional
    public void deleteCompetition(long id) {
        competitionRepository.deleteById(id);
    }
}
