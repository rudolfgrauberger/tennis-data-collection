package xyz.grauberger.application.idmapping.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xyz.grauberger.application.idmapping.core.entities.PlayerMappingEntity;
import xyz.grauberger.application.idmapping.core.entities.PlayerMappingId;

@Repository
public interface PlayerMappingEntityRepository extends JpaRepository<PlayerMappingEntity, PlayerMappingId> {
}
