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
import xyz.grauberger.application.masterdata.entities.match.Match;

@Embeddable
public record MatchMappingId(
    @ManyToOne @OnDelete(action = OnDeleteAction.CASCADE) Match match,
    @Enumerated(EnumType.STRING) @Column(columnDefinition = "TEXT") DataProvider provider) implements Serializable {

    public static MatchMappingId of(Match match, DataProvider provider) {
        return new MatchMappingId(match, provider);
    }
}
