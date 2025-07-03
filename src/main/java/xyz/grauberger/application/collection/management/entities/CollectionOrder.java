package xyz.grauberger.application.collection.management.entities;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import xyz.grauberger.application.masterdata.entities.match.Match;

@Getter
@Entity
public class CollectionOrder {

    enum Status {
        PENDING, // Order is pending collection
        COLLECTED // Order has been collected
    }

    @Id
    private Long id;

    @Setter
    @NotNull
    private Instant dueDate;

    @Setter
    @Column(columnDefinition = "TEXT")
    private String assignedCollector;

    @NotNull
    @Column(columnDefinition = "TEXT")
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    protected CollectionOrder() {
        // Default constructor for JPA
    }

    public CollectionOrder(Match match, Instant dueDate, String assignedCollector) {
        this.id = match.getId();
        this.dueDate = dueDate;
        this.assignedCollector = assignedCollector;
        this.status = Status.PENDING;
    }

    public void markAsCollected() {
        this.status = Status.COLLECTED;
    }

    public boolean isCollected() {
        return this.status == Status.COLLECTED;
    }
}
