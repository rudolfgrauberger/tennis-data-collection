package xyz.grauberger.application.collection.management.entities;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class MatchResult {
    @Id
    private Long matchId;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String result;

    @NotNull
    @CreatedDate
    @Column(updatable = false)
    private Instant createdDate;

    protected MatchResult() {
        // Default constructor for JPA
    }

    public MatchResult(long matchId, String result) {
        this.matchId = matchId;
        this.result = result;
    }
}
