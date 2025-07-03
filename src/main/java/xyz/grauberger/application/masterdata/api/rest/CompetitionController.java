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
import xyz.grauberger.application.masterdata.dtos.competition.CompetitionDetailDto;
import xyz.grauberger.application.masterdata.dtos.competition.CompetitionListDto;
import xyz.grauberger.application.masterdata.dtos.competition.CompetitionInputDto;
import xyz.grauberger.application.masterdata.services.CompetitionService;

@Tag(name = "Competition Management", description = "APIs for managing competitions in the system.")
@RestController
@RequestMapping(value = "/api/v1/competitions")
@RequiredArgsConstructor
public class CompetitionController {
    private final CompetitionService competitionService;


    @GetMapping(produces = "application/json")
    public List<CompetitionListDto> getCompetitions() {
        return competitionService.getAllCompetitions();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public CompetitionDetailDto getCompetitionById(@PathVariable long id) {
        return competitionService.getCompetitionDetailsById(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public CompetitionDetailDto createCompetition(@Valid @RequestBody CompetitionInputDto competitionCreateDto) {
        return competitionService.createCompetition(competitionCreateDto);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public CompetitionDetailDto updateCompetition(@PathVariable Long id, @Valid @RequestBody CompetitionInputDto competitionUpdateDto) {
        return competitionService.updateCompetition(id, competitionUpdateDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCompetition(@PathVariable Long id) {
        competitionService.deleteCompetition(id);
    }
}
