package xyz.grauberger.application.masterdata.entities;

public enum Surface {
    HARD("Hard"),
    CLAY("Clay"),
    GRASS("Grass");

    private final String displayName;

    Surface(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
