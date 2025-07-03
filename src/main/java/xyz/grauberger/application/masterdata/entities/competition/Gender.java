package xyz.grauberger.application.masterdata.entities.competition;

public enum Gender {
    MEN("Men"),
    WOMEN("Women"),
    MIXED("Mixed");

    private final String displayName;

    Gender(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
