package xyz.grauberger.application.masterdata.entities.competition;

public enum Type {
    SINGLES("Singles"),
    DOUBLES("Doubles");

    private final String displayName;

    Type(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
