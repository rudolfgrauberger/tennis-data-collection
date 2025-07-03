package xyz.grauberger.application.fdi.provider;

public enum DataProvider {
    EXAMPLE_COM("Example.com"),
    OTHER_API("grauberger.xyz"),;

    private final String name;
    DataProvider(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
