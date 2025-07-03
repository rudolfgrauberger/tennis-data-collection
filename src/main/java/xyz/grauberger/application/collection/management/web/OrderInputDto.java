package xyz.grauberger.application.collection.management.web;

import java.time.Instant;

import jakarta.validation.constraints.NotNull;

public record OrderInputDto(@NotNull Instant dueDate, @NotNull String assignedCollector) {
}
