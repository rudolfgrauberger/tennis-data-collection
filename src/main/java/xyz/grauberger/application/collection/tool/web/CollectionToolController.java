package xyz.grauberger.application.collection.tool.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import xyz.grauberger.application.collection.tool.ToolService;
import xyz.grauberger.application.collection.tool.domain.OrderStatisticsDto;
import xyz.grauberger.application.collection.tool.dto.EventDetailDto;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(name = "Collection Tool", description = "Endpoints for collecting events")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/collection/orders/{orderId}")
class CollectionToolController {
    private final ToolService toolService;

    @Operation(summary = "Get all events for a collection order")
    @GetMapping(path = "/events", produces = "application/json")
    public List<EventDetailDto> getEvents(@PathVariable long orderId) {
        return toolService.getEvents(orderId);
    }

    @Operation(summary = "Create a new event for a collection order")
    @PostMapping(path = "/events", produces = "application/json", consumes = "application/json")
    public EventDetailDto createEvent(@PathVariable long orderId, @Valid @RequestBody EventInputDto event) {
        return toolService.createEvent(orderId, event.type(), event.playerId());
    }

    @Operation(summary = "Update an existing event for a collection order")
    @PutMapping(path = "/events/{eventId}", produces = "application/json", consumes = "application/json")
    public EventDetailDto updateEvent(@PathVariable long orderId, @PathVariable long eventId, @Valid @RequestBody EventInputDto event) {
        return toolService.updateEvent(orderId, eventId, event.type(), event.playerId());
    }

    @Operation(summary = "Delete an event for a collection order")
    @DeleteMapping(path = "/events/{eventId}")
    public void deleteEvent(@PathVariable long orderId, @PathVariable long eventId) {
        toolService.deleteEvent(orderId, eventId);
    }

    @Operation(summary = "Mark a collection order as completed and compute the match results")
    @PostMapping(produces = "application/json")
    public OrderStatisticsDto completed(@PathVariable long orderId) {
        return toolService.complete(orderId);
    }

    @Operation(summary = "Get the video URL for a match in a collection order")
    @GetMapping(path = "/video", produces = "application/json")
    public String getVideoUrl(@PathVariable long orderId) {
        return toolService.getVideo(orderId);
    }

}
