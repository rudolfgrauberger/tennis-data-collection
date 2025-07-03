package xyz.grauberger.application.fdi.provider.otherapi.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xyz.grauberger.application.fdi.provider.otherapi.entities.OtherApiMatch;

@Repository
public interface OtherApiMatchRepository extends JpaRepository<OtherApiMatch, UUID> {
}
