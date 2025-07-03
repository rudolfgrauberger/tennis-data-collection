package xyz.grauberger.application.fdi.provider.examplecom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xyz.grauberger.application.fdi.provider.examplecom.entities.ExampleComCompetition;

@Repository
public interface ExampleComCompetitionRepository extends JpaRepository<ExampleComCompetition, String> {
}
