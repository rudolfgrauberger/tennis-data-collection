package xyz.grauberger.application.masterdata.shared;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;

@MappedSuperclass
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class AuditingEntity {
    @NotNull
    @CreatedDate
    @Column(updatable = false)
    private Instant createdAt;

    @CreatedBy
    @Column(updatable = false)
    private UUID createdBy;

    @LastModifiedDate
    private Instant lastModifiedAt;

    @LastModifiedBy
    private UUID lastModifiedBy;
}

