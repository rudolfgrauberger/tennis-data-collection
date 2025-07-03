package xyz.grauberger.application.collection.tool.dto;

import xyz.grauberger.application.collection.tool.persistence.EventType;
import xyz.grauberger.application.masterdata.entities.player.Gender;

public record EventDetailDto(long id,
    EventType type,
    Player player) {
    public record Player(
        long id,
        String firstName,
        String lastName,
        Gender gender
    ) {}
}
