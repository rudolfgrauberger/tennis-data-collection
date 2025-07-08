package xyz.grauberger.application.masterdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import xyz.grauberger.application.fdi.DataProvider;
import xyz.grauberger.application.masterdata.entities.mapping.MatchMappingEntity;
import xyz.grauberger.application.masterdata.entities.mapping.MatchMappingId;

@Repository
public interface MatchMappingEntityRepository extends JpaRepository<MatchMappingEntity, MatchMappingId> {

    @Query("SELECT m FROM MatchMappingEntity m WHERE m.id.match.id = ?1 AND m.id.provider = ?2")
    public MatchMappingEntity findByIdAndProvider(long id, DataProvider provider);

    @Query("SELECT m FROM MatchMappingEntity m WHERE m.providerId = ?1 AND m.id.provider = ?2")
    public MatchMappingEntity findIdByProviderIdAndProvider(String providerId, DataProvider provider);
}
