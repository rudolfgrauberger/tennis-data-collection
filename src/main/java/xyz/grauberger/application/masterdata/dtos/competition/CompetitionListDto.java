package xyz.grauberger.application.masterdata.dtos.competition;

public record CompetitionListDto(
    long id,
    String name,
    String playStyle,
    String locationName,
    String surface,
    String tournament
    ) {
}
