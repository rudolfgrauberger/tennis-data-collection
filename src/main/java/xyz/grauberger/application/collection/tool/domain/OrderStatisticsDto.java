package xyz.grauberger.application.collection.tool.domain;

public record OrderStatisticsDto(int eventCount, int gameCount, int serveCount, int winCount) {

    public OrderStatisticsDto {
        if (eventCount < 0 || serveCount < 0 || winCount < 0) {
            throw new IllegalArgumentException("Counts must be non-negative");
        }
    }

    public static OrderStatisticsDto empty() {
        return new OrderStatisticsDto(0, 0, 0, 0);
    }
}
