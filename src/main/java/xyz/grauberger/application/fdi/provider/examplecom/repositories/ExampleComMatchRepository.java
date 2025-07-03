package xyz.grauberger.application.fdi.provider.examplecom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xyz.grauberger.application.fdi.provider.examplecom.entities.ExampleComMatch;

@Repository
public interface ExampleComMatchRepository extends JpaRepository<ExampleComMatch, String> {
}
