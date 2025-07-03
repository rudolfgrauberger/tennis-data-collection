package xyz.grauberger.application.masterdata.entities.player;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.grauberger.application.masterdata.shared.AuditingEntity;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Entity
public class Player extends AuditingEntity {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String firstName;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String lastName;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "TEXT")
    private Gender gender;

    @Setter
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    public Player(String firstName, String lastName, Gender gender, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public Player update(String firstName, String lastName, Gender gender, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public String getName() {
        return firstName + " " + lastName;
    }
}
