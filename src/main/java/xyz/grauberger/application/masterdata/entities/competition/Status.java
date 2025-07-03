package xyz.grauberger.application.masterdata.entities.competition;

public enum Status {
    PLANNED("Planned"),
    ACTIVE("Active"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled");

    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
