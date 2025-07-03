package xyz.grauberger.application.collection.tool.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import xyz.grauberger.application.collection.management.entities.CollectionOrder;
import xyz.grauberger.application.masterdata.entities.player.Player;

@Getter
@Entity
public class Event {
    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @NotNull
    @Column(columnDefinition = "TEXT")
    @Enumerated(EnumType.STRING)
    private EventType type;

    @Setter
    @NotNull
    @ManyToOne
    private Player player;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "collection_order_id")
    private CollectionOrder collectionOrder;

    protected Event() {
        // Default constructor for JPA
    }

    public Event(EventType type, Player player, CollectionOrder collectionOrder) {
        this.type = type;
        this.player = player;
        this.collectionOrder = collectionOrder;
    }

    public void updateEvent(EventType type, Player player) {
        this.type = type;
        this.player = player;
    }
}
