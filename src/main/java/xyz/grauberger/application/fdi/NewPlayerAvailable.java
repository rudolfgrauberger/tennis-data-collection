package xyz.grauberger.application.fdi;

import java.time.LocalDate;

public record NewPlayerAvailable(String firstName, String lastName, Gender gender, LocalDate birthDate) {
    public enum Gender {
        UNKNOWN,
        MALE,
        FEMALE
    }
}
