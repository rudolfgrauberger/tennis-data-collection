package xyz.grauberger.application.masterdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.grauberger.application.masterdata.entities.match.SingleMatch;

public interface SingleMatchRepository extends JpaRepository<SingleMatch, Long>{
}
