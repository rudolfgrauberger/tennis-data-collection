package xyz.grauberger.application.masterdata.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import xyz.grauberger.application.masterdata.entities.match.Match;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByCompetitionId(long competitionId, Pageable pageable);
    Optional<Match> findByName(String name);
}
