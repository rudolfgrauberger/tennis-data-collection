package xyz.grauberger.application.masterdata.api.rest;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import xyz.grauberger.application.masterdata.dtos.match.MatchDetailDto;
import xyz.grauberger.application.masterdata.dtos.match.MatchInputDto;
import xyz.grauberger.application.masterdata.dtos.match.MatchListDto;
import xyz.grauberger.application.masterdata.services.MatchService;

@Tag(name = "Match Management", description = "APIs for managing matches in the system.")
@RestController
@RequestMapping(value = "/api/v1/matches")
@RequiredArgsConstructor
public class MatchController {
    private final MatchService matchService;

    @GetMapping(produces = "application/json")
    public List<MatchListDto> getMatches(@RequestParam(required = false) Long competitionId, @Parameter(hidden = true) Pageable pageable) {
        return matchService.getMatches(competitionId, pageable);
    }

    @Operation(
        summary = "Get Match by ID",
        description = """
            Retrieve match details by match ID. There are two types of matches: SingleMatchDetailDto and DoubleMatchDetailDto.
            """)
    @GetMapping(value = "/{id}", produces = "application/json")
    public MatchDetailDto getMatchById(@PathVariable long id) {
        return matchService.getMatchDetailsById(id);
    }

    @Operation(
        summary = "Create Match",
        description = """
            Create a new match. The type of match (see SingleMatchInputDto or DoubleMatchInputDto) is determined by the input data.
            """)
    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public MatchDetailDto createSingleMatch(@Valid @RequestBody MatchInputDto matchCreateDto) {
        return matchService.createMatch(matchCreateDto);
    }

    @Operation(
        summary = "Update Match",
        description = """
            Update the match. The input data must be of type SingeMatchInputDto or DoubleMatchInputDto.
            """)
    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public MatchDetailDto updateMatch(@PathVariable long id, @Valid @RequestBody MatchInputDto matchUpdateDto) {
        return matchService.updateMatch(id, matchUpdateDto);
    }

    @Operation(
        summary = "Delete Match",
        description = "Delete a match by its ID.")
    @DeleteMapping("/{id}")
    public void deleteMatch(@PathVariable long id) {
        matchService.deleteMatch(id);
    }
}
