package xyz.grauberger.application.masterdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xyz.grauberger.application.masterdata.entities.mapping.PlayerMappingEntity;
import xyz.grauberger.application.masterdata.entities.mapping.PlayerMappingId;

@Repository
public interface PlayerMappingEntityRepository extends JpaRepository<PlayerMappingEntity, PlayerMappingId> {
}
