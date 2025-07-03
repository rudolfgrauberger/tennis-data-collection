package xyz.grauberger.application.masterdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.grauberger.application.masterdata.entities.match.DoubleMatch;

public interface DoubleMatchRepository extends JpaRepository<DoubleMatch, Long> {
}
