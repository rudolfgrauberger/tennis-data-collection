package xyz.grauberger.application.masterdata.entities.player;

public enum Gender {
    MEN("Men"),
    WOMEN("Women");

    private final String displayName;

    Gender(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
