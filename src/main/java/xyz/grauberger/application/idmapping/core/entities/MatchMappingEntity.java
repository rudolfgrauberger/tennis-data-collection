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
@Table(name = "match_mapping")
@EntityListeners(AuditingEntityListener.class)
public class MatchMappingEntity {
    @EmbeddedId
    private MatchMappingId id;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String providerId;

    @NotNull
    @CreatedDate
    @Column(updatable = false)
    private Instant createdAt;

    protected MatchMappingEntity() {
        // Default constructor for JPA
    }

    public String getProviderId() {
        return providerId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public MatchMappingId getId() {
        return id;
    }

    public MatchMappingEntity(MatchMappingId id, String providerId) {
        this.id = id;
        this.providerId = providerId;
    }
}
