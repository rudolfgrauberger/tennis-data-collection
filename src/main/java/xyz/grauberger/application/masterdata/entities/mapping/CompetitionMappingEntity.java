package xyz.grauberger.application.masterdata.entities.mapping;

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
@Table(name = "competition_mapping")
@EntityListeners(AuditingEntityListener.class)
public class CompetitionMappingEntity {
    @EmbeddedId
    private CompetitionMappingId id;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String providerId;

    @NotNull
    @CreatedDate
    @Column(updatable = false)
    private Instant createdAt;

    protected CompetitionMappingEntity() {
        // Default constructor for JPA
    }

    public CompetitionMappingId getId() {
        return id;
    }

    public String getProviderId() {
        return providerId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public CompetitionMappingEntity(CompetitionMappingId id, String providerId) {
        this.id = id;
        this.providerId = providerId;
    }
}
