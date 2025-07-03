package xyz.grauberger.application.collection.tool.web;

import jakarta.validation.constraints.NotNull;
import xyz.grauberger.application.collection.tool.persistence.EventType;

public record EventInputDto(@NotNull EventType type, @NotNull Long playerId) {
}
