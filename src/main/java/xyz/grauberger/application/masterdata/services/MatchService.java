package xyz.grauberger.application.masterdata.services;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import xyz.grauberger.application.masterdata.dtos.match.DoubleMatchDetailDto;
import xyz.grauberger.application.masterdata.dtos.match.MatchDetailDto;
import xyz.grauberger.application.masterdata.dtos.match.MatchInputDto;
import xyz.grauberger.application.masterdata.dtos.match.MatchListDto;
import xyz.grauberger.application.masterdata.dtos.match.SingleMatchDetailDto;
import xyz.grauberger.application.masterdata.entities.match.DoubleMatch;
import xyz.grauberger.application.masterdata.entities.match.Match;
import xyz.grauberger.application.masterdata.entities.match.SingleMatch;
import xyz.grauberger.application.masterdata.entities.match.Team;
import xyz.grauberger.application.masterdata.exceptions.CompetitionNotFoundException;
import xyz.grauberger.application.masterdata.exceptions.MatchNotFoundException;
import xyz.grauberger.application.masterdata.exceptions.PlayerNotFoundException;
import xyz.grauberger.application.masterdata.repositories.CompetitionRepository;
import xyz.grauberger.application.masterdata.repositories.DoubleMatchRepository;
import xyz.grauberger.application.masterdata.repositories.MatchRepository;
import xyz.grauberger.application.masterdata.repositories.PlayerRepository;
import xyz.grauberger.application.masterdata.repositories.SingleMatchRepository;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final SingleMatchRepository singleMatchRepository;
    private final DoubleMatchRepository doubleMatchRepository;
    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;
    private final CompetitionRepository competitionRepository;

    @Transactional(readOnly = true)
    public List<MatchListDto> getMatches(Long competitionId, Pageable pageable) {
        if (competitionId != null) {
            return matchRepository.findByCompetitionId(competitionId, pageable).stream()
                    .map(match -> new MatchListDto(match.getId(), match.getMatchDate(), match.getCompetition().getName(), match.getCompetition().getPlayStyle().toString(), match.getName()))
                    .toList();
        }

        return matchRepository.findAll(pageable).stream()
                .map(match -> new MatchListDto(match.getId(), match.getMatchDate(), match.getCompetition().getName(), match.getCompetition().getPlayStyle().toString(), match.getName()))
                .toList();
    }

    @Transactional(readOnly = true)
    public MatchDetailDto getMatchDetailsById(long id) {
        final var match = matchRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Match with ID " + id + " not found"));

        return convert(match);
    }

    @Transactional
    public MatchDetailDto createMatch(MatchInputDto matchCreateDto) {
        final var match = switch (matchCreateDto.getMatchType()) {
            case MatchInputDto.MatchType.SINGLE ->  createSingleMatch(matchCreateDto);
            case MatchInputDto.MatchType.DOUBLE -> createDoubleMatch(matchCreateDto);
        };

        final var savedMatch = matchRepository.save(match);

        return convert(savedMatch);
    }

    private DoubleMatch createDoubleMatch(MatchInputDto matchInput) {
        final var firstTeamFirstPlayer = playerRepository.findById(matchInput.firstTeamFirstPlayerId())
                .orElseThrow(() -> new PlayerNotFoundException("First team first player not found. Player-ID: " + matchInput.firstTeamFirstPlayerId()));

        final var firstTeamSecondPlayer = playerRepository.findById(matchInput.firstTeamSecondPlayerId())
                .orElseThrow(() -> new PlayerNotFoundException("First team second player not found. Player-ID: " + matchInput.firstTeamSecondPlayerId()));

        final var secondTeamFirstPlayer = playerRepository.findById(matchInput.secondTeamFirstPlayerId())
                .orElseThrow(() -> new PlayerNotFoundException("Second team first player not found. Player-ID: " + matchInput.secondTeamFirstPlayerId()));

        final var secondTeamSecondPlayer = playerRepository.findById(matchInput.secondTeamSecondPlayerId())
                .orElseThrow(() -> new PlayerNotFoundException("Second team second player not found. Player-ID: " + matchInput.secondTeamSecondPlayerId()));

        final var competition = competitionRepository.findById(matchInput.competitionId())
                .orElseThrow(() -> new CompetitionNotFoundException(matchInput.competitionId()));

        final var firstTeam = new Team(firstTeamFirstPlayer, firstTeamSecondPlayer);
        final var secondTeam = new Team(secondTeamFirstPlayer, secondTeamSecondPlayer);

        return new DoubleMatch(
            matchInput.matchDate(),
            competition,
            firstTeam,
            secondTeam
        );
    }

    private SingleMatch createSingleMatch(MatchInputDto matchInput) {
        final var firstPlayer = playerRepository.findById(matchInput.firstTeamFirstPlayerId())
                .orElseThrow(() -> new PlayerNotFoundException("First player not found. Player-ID: " + matchInput.firstTeamFirstPlayerId()));
        final var secondPlayer = playerRepository.findById(matchInput.secondTeamFirstPlayerId())
                .orElseThrow(() -> new IllegalArgumentException("Second player not found. Player-ID: " + matchInput.secondTeamFirstPlayerId()));

        final var competition = competitionRepository.findById(matchInput.competitionId())
                .orElseThrow(() -> new CompetitionNotFoundException(matchInput.competitionId()));

        return new SingleMatch(
            matchInput.matchDate(),
            competition,
            firstPlayer,
            secondPlayer
        );
    }

    private static MatchDetailDto convert(Match match) {
        return switch (match) {
            case SingleMatch singleMatch -> new SingleMatchDetailDto(
                singleMatch.getId(),
                singleMatch.getMatchDate(),
                singleMatch.getCompetition().getId(),
                singleMatch.getFirstPlayer().getId(),
                singleMatch.getSecondPlayer().getId()
            );
            case DoubleMatch doubleMatch -> new DoubleMatchDetailDto(
                doubleMatch.getId(),
                doubleMatch.getMatchDate(),
                doubleMatch.getCompetition().getId(),
                new DoubleMatchDetailDto.Team(
                    doubleMatch.getFirstTeam().firstPlayer().getId(),
                    doubleMatch.getFirstTeam().secondPlayer().getId()
                ),
                new DoubleMatchDetailDto.Team(
                    doubleMatch.getSecondTeam().firstPlayer().getId(),
                    doubleMatch.getSecondTeam().secondPlayer().getId()
                )
            );
            default -> throw new IllegalArgumentException("Match with ID %d is of unknown type %s".formatted(match.getId(), match.getClass().getSimpleName()));
        };
    }

    @Transactional
    public MatchDetailDto updateMatch(long id, MatchInputDto matchUpdateDto) {
        final var match = matchRepository.findById(id)
                .orElseThrow(() -> new MatchNotFoundException(id));

        if (match instanceof SingleMatch && matchUpdateDto.getMatchType() != MatchInputDto.MatchType.SINGLE) {
            throw new IllegalArgumentException("Cannot update a single match to a double match");
        } else if (match instanceof DoubleMatch && matchUpdateDto.getMatchType() != MatchInputDto.MatchType.DOUBLE) {
            throw new IllegalArgumentException("Cannot update a double match to a single match");
        }

        final Match updatedMatch = switch (matchUpdateDto.getMatchType()) {
            case MatchInputDto.MatchType.SINGLE -> updateSingleMatch(id, matchUpdateDto);
            case MatchInputDto.MatchType.DOUBLE -> updateDoubleMatch(id, matchUpdateDto);
        };

        return convert(updatedMatch);
    }

    private DoubleMatch updateDoubleMatch(long id, MatchInputDto matchInput) {
        final var firstTeamFirstPlayer = playerRepository.findById(matchInput.firstTeamFirstPlayerId())
                .orElseThrow(() -> new PlayerNotFoundException("First team first player not found. Player-ID: " + matchInput.firstTeamFirstPlayerId()));

        final var firstTeamSecondPlayer = playerRepository.findById(matchInput.firstTeamSecondPlayerId())
                .orElseThrow(() -> new PlayerNotFoundException("First team second player not found. Player-ID: " + matchInput.firstTeamSecondPlayerId()));

        final var secondTeamFirstPlayer = playerRepository.findById(matchInput.secondTeamFirstPlayerId())
                .orElseThrow(() -> new PlayerNotFoundException("Second team first player not found. Player-ID: " + matchInput.secondTeamFirstPlayerId()));

        final var secondTeamSecondPlayer = playerRepository.findById(matchInput.secondTeamSecondPlayerId())
                .orElseThrow(() -> new PlayerNotFoundException("Second team second player not found. Player-ID: " + matchInput.secondTeamSecondPlayerId()));

        final var competition = competitionRepository.findById(matchInput.competitionId())
                .orElseThrow(() -> new CompetitionNotFoundException(matchInput.competitionId()));

        final var firstTeam = new Team(firstTeamFirstPlayer, firstTeamSecondPlayer);
        final var secondTeam = new Team(secondTeamFirstPlayer, secondTeamSecondPlayer);

        final var existingMatch = doubleMatchRepository.findById(id)
                .orElseThrow(() -> new MatchNotFoundException("Double match not found. Double Match-ID: " + id));

        existingMatch.update(matchInput.matchDate(), competition, firstTeam, secondTeam);

        return existingMatch;
    }

    private SingleMatch updateSingleMatch(long id, MatchInputDto matchInput) {
        final var firstPlayer = playerRepository.findById(matchInput.firstTeamFirstPlayerId())
                .orElseThrow(() -> new PlayerNotFoundException("First player not found. Player-ID: " + matchInput.firstTeamFirstPlayerId()));
        final var secondPlayer = playerRepository.findById(matchInput.secondTeamFirstPlayerId())
                .orElseThrow(() -> new PlayerNotFoundException("Second player not found. Player-ID: " + matchInput.secondTeamFirstPlayerId()));

        final var competition = competitionRepository.findById(matchInput.competitionId())
                .orElseThrow(() -> new CompetitionNotFoundException(matchInput.competitionId()));

        final var existingMatch = singleMatchRepository.findById(id)
                .orElseThrow(() -> new MatchNotFoundException("Single match not found. Single Match-ID: " + id));

        existingMatch.update(matchInput.matchDate(), competition, firstPlayer, secondPlayer);

        return existingMatch;
    }

    @Transactional
    public void deleteMatch(long id) {
        matchRepository.deleteById(id);
    }
}
