package xyz.grauberger.application.fdi.provider.otherapi.entities;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(schema = "fdi", name = "otherapi_match")
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
public class OtherApiMatch {
    @Id
    private UUID id;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String name;

    private LocalDate matchDate;

    @NotNull
    @ManyToOne
    private OtherApiPlayer teamOneFirstPlayer;
    @NotNull
    @ManyToOne
    private OtherApiPlayer teamTwoFirstPlayer;

    @ManyToOne
    private OtherApiPlayer teamOneSecondPlayer;

    @ManyToOne
    private OtherApiPlayer teamTwoSecondPlayer;

    @NotNull
    @ManyToOne
    private OtherApiCompetition competition;

    public static OtherApiMatch ofSingle(UUID id, String name, LocalDate matchDate, OtherApiPlayer teamOneFirstPlayer,
            OtherApiPlayer teamTwoFirstPlayer, OtherApiCompetition competition) {
        return new OtherApiMatch(id, name, matchDate, teamOneFirstPlayer, teamTwoFirstPlayer,
                null, null, competition);
    }

    public static OtherApiMatch ofDouble(UUID id, String name, LocalDate matchDate, OtherApiPlayer teamOneFirstPlayer,
            OtherApiPlayer teamOneSecondPlayer, OtherApiPlayer teamTwoFirstPlayer, OtherApiPlayer teamTwoSecondPlayer,
            OtherApiCompetition competition) {
        return new OtherApiMatch(id, name, matchDate, teamOneFirstPlayer, teamTwoFirstPlayer,
            teamOneSecondPlayer, teamTwoSecondPlayer, competition);
    }
}
