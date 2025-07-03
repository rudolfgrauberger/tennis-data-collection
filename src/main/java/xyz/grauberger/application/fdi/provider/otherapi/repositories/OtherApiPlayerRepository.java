package xyz.grauberger.application.fdi.provider.otherapi.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xyz.grauberger.application.fdi.provider.otherapi.entities.OtherApiPlayer;

@Repository
public interface OtherApiPlayerRepository extends JpaRepository<OtherApiPlayer, UUID> {
    Optional<OtherApiPlayer> findByName(String name);
}
