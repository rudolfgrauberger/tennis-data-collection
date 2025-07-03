package xyz.grauberger.application.masterdata.entities.competition;

import java.util.List;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

@Embeddable
public record PlayStyle(@NotNull @Enumerated(EnumType.STRING) Type type, @NotNull @Enumerated(EnumType.STRING) Gender gender) {
    public PlayStyle {
        if (Gender.MIXED.equals(gender) && !Type.DOUBLES.equals(type)) {
            throw new IllegalArgumentException("Mixed competitions can only be of type DOUBLES");
        }
    }

    public static PlayStyle from(String id) {
        final var parts = id.split("_");

        if (parts.length != 2) {
            final var errorMessage = "Invalid PlayStyle ID format. Expected format: '[0-%s]_[0-%s]'".formatted(Type.values().length-1, Gender.values().length-1);
            throw new IllegalArgumentException(errorMessage);
        }

        final var type = Type.values()[Integer.parseInt(parts[0])];
        final var gender = Gender.values()[Integer.parseInt(parts[1])];

        return of(type, gender);
    }

    public static PlayStyle of(Type type, Gender gender) {
        return new PlayStyle(type, gender);
    }

    public static PlayStyle womensSingles() {
        return new PlayStyle(Type.SINGLES, Gender.WOMEN);
    }

    public static PlayStyle mensSingles() {
        return new PlayStyle(Type.SINGLES, Gender.MEN);
    }

    public static PlayStyle womensDoubles() {
        return new PlayStyle(Type.DOUBLES, Gender.WOMEN);
    }

    public static PlayStyle mensDoubles() {
        return new PlayStyle(Type.DOUBLES, Gender.MEN);
    }

    public static PlayStyle mixedDoubles() {
        return new PlayStyle(Type.DOUBLES, Gender.MIXED);
    }

    @Override
    public String toString() {
        if (gender == Gender.MIXED) {
            return "Mixed Doubles";
        } else {
            final var genderString = this.gender.getDisplayName() + "'s";
            final var typeString = this.type.getDisplayName();
            return genderString + " " + typeString;
        }
    }

    public String id() {
        return type.ordinal() + "_" + gender.ordinal();
    }

    public static List<PlayStyle> values() {
        return List.of(
                mensSingles(),
                womensSingles(),
                mensDoubles(),
                womensDoubles(),
                mixedDoubles());
    }
}

