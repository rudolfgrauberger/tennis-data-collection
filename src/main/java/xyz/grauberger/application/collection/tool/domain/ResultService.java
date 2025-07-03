package xyz.grauberger.application.collection.tool.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import xyz.grauberger.application.collection.tool.domain.KPI.KpiName;
import xyz.grauberger.application.collection.tool.persistence.Event;

@Service
public class ResultService {
    public record MatchResult(Map<Integer, List<KPI>> kpis) {
        public List<KPI> getKpisForGame(Integer game) {
            return kpis.get(game);
        }

        public List<KPI> getAllKpis() {
            return kpis.values().stream()
                .flatMap(List::stream)
                .toList();
        }

        public Integer getNumberOfGames() {
            return kpis.size();
        }

        public int getWonGames(long playerId) {
            return (int) kpis.values().stream()
                .filter(kpis -> kpis.stream().anyMatch(kpi -> kpi.name() == KPI.KpiName.WINS && kpi.playerId() == playerId))
                .count();
        }

        public int getCount(KpiName kpiName) {
            return (int) kpis.values().stream()
                .flatMap(List::stream)
                .filter(kpi -> kpi.name() == kpiName)
                .count();
        }

        public String asReadableNotion() {
            return "6⁴:7⁷, 6:4, 6:1, 6:3";
        }
    }

    public MatchResult computeResults(List<Event> events) {
        final var kpis = new HashMap<Integer, List<KPI>>();
        int currentGame = 1;

        for (final var event : events) {
            final var roundKpis = kpis.computeIfAbsent(currentGame, _ -> new ArrayList<>());
            final var playerId = event.getPlayer().getId();

            final var kpiName = switch (event.getType()) {
                    case SERVE -> KPI.KpiName.SERVES;
                    case WINS -> KPI.KpiName.WINS;
                };

            roundKpis.add(KPI.count(kpiName, playerId));

            if (kpiName == KPI.KpiName.WINS) {
                currentGame++;
            }
        }

        return new MatchResult(kpis);
    }
}
