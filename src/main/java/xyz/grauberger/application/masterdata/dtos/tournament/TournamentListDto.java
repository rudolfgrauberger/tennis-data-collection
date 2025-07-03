package xyz.grauberger.application.masterdata.dtos.tournament;

public record TournamentListDto (
    long id,
    String name,
    String locationName,
    String surface,
    String description
) {}
