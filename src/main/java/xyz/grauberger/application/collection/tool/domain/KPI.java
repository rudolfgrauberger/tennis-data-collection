package xyz.grauberger.application.collection.tool.domain;

public record KPI(KpiName name, long value, long playerId) {

    public enum KpiName {
        SERVES,
        WINS,
    }

    public static KPI count(KpiName name, long playerId) {
        return new KPI(name, 1, playerId);
    }
}
