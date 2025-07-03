package xyz.grauberger.application.masterdata.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.grauberger.application.masterdata.entities.competition.Competition;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    Optional<Competition> findByName(String name);
}
