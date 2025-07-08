package xyz.grauberger.application.masterdata.entities.mapping;

import java.io.Serializable;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import xyz.grauberger.application.fdi.DataProvider;
import xyz.grauberger.application.masterdata.entities.competition.Competition;

@Embeddable
public record CompetitionMappingId(
        @ManyToOne @OnDelete(action = OnDeleteAction.CASCADE) Competition competition,
        @Enumerated(EnumType.STRING) @Column(columnDefinition = "TEXT") DataProvider provider) implements Serializable {

    public static CompetitionMappingId of(Competition competition, DataProvider provider) {
        return new CompetitionMappingId(competition, provider);
    }
}
