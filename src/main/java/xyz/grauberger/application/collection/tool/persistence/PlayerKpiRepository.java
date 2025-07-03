package xyz.grauberger.application.collection.tool.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerKpiRepository extends CrudRepository<PlayerKpi, Long> {
}
