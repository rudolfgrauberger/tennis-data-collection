package xyz.grauberger.application.masterdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.grauberger.application.masterdata.entities.tournament.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
}
