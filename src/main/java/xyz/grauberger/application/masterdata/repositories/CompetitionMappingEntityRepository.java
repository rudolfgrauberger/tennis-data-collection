package xyz.grauberger.application.masterdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xyz.grauberger.application.masterdata.entities.mapping.CompetitionMappingEntity;
import xyz.grauberger.application.masterdata.entities.mapping.CompetitionMappingId;

@Repository
public interface CompetitionMappingEntityRepository extends JpaRepository<CompetitionMappingEntity, CompetitionMappingId> {
}
