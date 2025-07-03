package xyz.grauberger.application.idmapping.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xyz.grauberger.application.idmapping.core.entities.CompetitionMappingEntity;
import xyz.grauberger.application.idmapping.core.entities.CompetitionMappingId;

@Repository
public interface CompetitionMappingEntityRepository extends JpaRepository<CompetitionMappingEntity, CompetitionMappingId> {
}
