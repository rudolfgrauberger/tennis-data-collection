package xyz.grauberger.application.collection.management.repositories;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import lombok.Value;
import xyz.grauberger.application.collection.management.entities.CollectionOrder;

@Repository
public interface CollectionOrderRepository extends CrudRepository<CollectionOrder, Long> {

    @Value
    public class MatchToCollectDto {
        long id;
        String name;
        LocalDate matchDate;
        Instant dueDate;
        String competitionName;
        String collector;

        public MatchToCollectDto(long id, String name, String matchDate, Instant dueDate, String competitionName, String collector) {
            this.id = id;
            this.name = name;
            this.matchDate = Objects.nonNull(matchDate) ? LocalDate.parse(matchDate) : null;
            this.dueDate = dueDate;
            this.competitionName = competitionName;
            this.collector = collector;
        }
    }

    @Value
    public class CollectedMatchDto {
        long id;
        String name;
        LocalDate matchDate;
        String competitionName;
        String result;
        String assignedCollector;

        public CollectedMatchDto(long id, String name, String matchDate, String competitionName, String result, String assignedCollector) {
            this.id = id;
            this.name = name;
            this.matchDate = Objects.nonNull(matchDate) ? LocalDate.parse(matchDate) : null;
            this.competitionName = competitionName;
            this.result = result;
            this.assignedCollector = assignedCollector;
        }
    }

    @Query(value = """
        SELECT m.id, m.name, TO_CHAR(m.match_date, 'YYYY-MM-DD'), co.due_date, c.name as competition_name, co.assigned_collector
        FROM match m
        JOIN competition c ON m.competition_id = c.id
        LEFT JOIN collection_order co ON co.id = m.id
        WHERE co.status IS NULL OR co.status = 'PENDING'
        ORDER BY m.match_date
        """, nativeQuery = true)
    public List<MatchToCollectDto> findAllNextMatchesToOrder();

    @Query(value = """
        SELECT m.id, m.name, TO_CHAR(m.match_date, 'YYYY-MM-DD'), c.name as competition_name, r.result, co.assigned_collector
        FROM match m
        JOIN competition c ON m.competition_id = c.id
        JOIN collection_order co ON (co.id = m.id AND co.status = 'COLLECTED')
        JOIN match_result r ON r.match_id = m.id
        ORDER BY m.match_date
        """, nativeQuery = true)
    public List<CollectedMatchDto> findAllCollectedMatches();
}
