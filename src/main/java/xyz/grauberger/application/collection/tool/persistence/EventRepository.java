package xyz.grauberger.application.collection.tool.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    List<Event> findByCollectionOrderId(Long collectionOrderId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("DELETE FROM Event e WHERE e.id = ?1")
    void deleteById(long id);
}
