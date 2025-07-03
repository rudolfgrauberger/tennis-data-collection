package xyz.grauberger.application.fdi.provider.otherapi.entities;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(schema = "fdi", name = "otherapi_player")
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
public class OtherApiPlayer {
    @Id
    private UUID id;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String name;

    private LocalDate birthDate;

    @NotNull
    @Column(columnDefinition = "TEXT")
    @Enumerated(EnumType.STRING)
    private OtherApiGender gender;
}
