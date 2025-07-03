package xyz.grauberger.application.idmapping.core.entities;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "player_mapping")
@EntityListeners(AuditingEntityListener.class)
public class PlayerMappingEntity {
    @EmbeddedId
    private PlayerMappingId id;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String providerId;

    @NotNull
    @CreatedDate
    @Column(updatable = false)
    private Instant createdAt;

    protected PlayerMappingEntity() {
        // Default constructor for JPA
    }

    public PlayerMappingId getId() {
        return id;
    }

    public String getProviderId() {
        return providerId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public PlayerMappingEntity(PlayerMappingId id, String providerId) {
        this.id = id;
        this.providerId = providerId;
    }
}
