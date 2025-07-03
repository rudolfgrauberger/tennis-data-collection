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
import xyz.grauberger.application.masterdata.dtos.tournament.TournamentDetailDto;
import xyz.grauberger.application.masterdata.dtos.tournament.TournamentListDto;
import xyz.grauberger.application.masterdata.dtos.tournament.TournamentInputDto;
import xyz.grauberger.application.masterdata.services.TournamentService;

@Tag(name = "Tournament Management", description = "APIs for managing tournaments in the system.")
@RestController
@RequestMapping(value = "/api/v1/tournaments")
@RequiredArgsConstructor
public class TournamentController {
    private final TournamentService tournamentService;

    @GetMapping(produces = "application/json")
    public List<TournamentListDto> getAllTournaments() {
        return tournamentService.getAllTournaments();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public TournamentDetailDto getTournamentById(@PathVariable long id) {
        return tournamentService.getTournamentDetailsById(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public TournamentDetailDto createTournament(@Valid @RequestBody TournamentInputDto tournament) {
        return tournamentService.createTournament(tournament);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public TournamentDetailDto updateTournament(@PathVariable long id, @Valid @RequestBody TournamentInputDto tournament) {
        return tournamentService.updateTournament(id, tournament);
    }

    @DeleteMapping("/{id}")
    public void deleteTournament(@PathVariable long id) {
        tournamentService.deleteTournament(id);
    }
}
