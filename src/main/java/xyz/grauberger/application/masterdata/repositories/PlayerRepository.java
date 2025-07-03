package xyz.grauberger.application.masterdata.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import xyz.grauberger.application.masterdata.entities.player.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Transactional(readOnly = true)
    @Query("SELECT p FROM Player p WHERE CONCAT(p.firstName, ' ', p.lastName) = :name")
    Optional<Player> findByName(String name);
}
