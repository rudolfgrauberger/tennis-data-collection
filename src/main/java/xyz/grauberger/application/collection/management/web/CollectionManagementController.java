package xyz.grauberger.application.collection.management.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import xyz.grauberger.application.collection.management.CollectionManagementService;
import xyz.grauberger.application.collection.management.repositories.CollectionOrderRepository;

@Tag(name = "Collection Management", description = "Endpoints for managing collection orders")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/collection/management")
class CollectionManagementController {
    private final CollectionManagementService collectionManagementService;

    @Operation(summary = "Get all matches that need to be collected")
    @GetMapping(path = "/orders", produces = "application/json")
    public List<CollectionOrderRepository.MatchToCollectDto> getMatchesToCollect() {
        return collectionManagementService.getMatchesToCollect();
    }

    @Operation(summary = "Get all already collected matches")
    @GetMapping(path = "/collected", produces = "application/json")
    public List<CollectionOrderRepository.CollectedMatchDto> getCollectedMatches() {
        return collectionManagementService.getCollectedMatches();
    }

    @Operation(summary = "Creates or updates a collection order with a due date and collector")
    @PutMapping(path = "/orders/{id}", produces = "application/json", consumes = "application/json")
    public CollectionManagementService.CollectionOrderDto setDueDate(@PathVariable long id, @Valid @RequestBody OrderInputDto order) {
        return collectionManagementService.setDueDate(id, order.dueDate(), order.assignedCollector());
    }
}
