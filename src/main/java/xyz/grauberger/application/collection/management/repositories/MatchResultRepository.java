package xyz.grauberger.application.collection.management.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import xyz.grauberger.application.collection.management.entities.MatchResult;

@Repository
public interface MatchResultRepository extends CrudRepository<MatchResult, Long> {

    /**
     * Custom method to find a MatchResult by its matchId.
     *
     * @param matchId the ID of the match
     * @return the MatchResult if found, otherwise null
     */
    Optional<MatchResult> findByMatchId(Long matchId);

    /**
     * Custom method to delete a MatchResult by its matchId.
     *
     * @param matchId the ID of the match
     */
    void deleteByMatchId(Long matchId);

}
