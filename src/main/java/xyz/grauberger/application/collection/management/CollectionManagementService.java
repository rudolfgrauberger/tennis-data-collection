package xyz.grauberger.application.collection.management;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import xyz.grauberger.application.collection.management.entities.CollectionOrder;
import xyz.grauberger.application.collection.management.repositories.CollectionOrderRepository;
import xyz.grauberger.application.masterdata.entities.match.Match;
import xyz.grauberger.application.masterdata.exceptions.MatchNotFoundException;
import xyz.grauberger.application.masterdata.repositories.MatchRepository;

@Service
@RequiredArgsConstructor
public class CollectionManagementService {
    public record CollectionOrderDto(long id, String name, Instant dueDate, String assignedCollector) {}
    private final CollectionOrderRepository collectionOrderRepository;
    private final MatchRepository matchRepository;

    @Transactional(readOnly = true)
    public List<CollectionOrderRepository.MatchToCollectDto> getMatchesToCollect() {
        return collectionOrderRepository.findAllNextMatchesToOrder();
    }

    @Transactional(readOnly = true)
    public List<CollectionOrderRepository.CollectedMatchDto> getCollectedMatches() {
        return collectionOrderRepository.findAllCollectedMatches();
    }

    @Transactional
    public CollectionOrderDto setDueDate(long id, Instant dueDate, String collector) {
        final var match = matchRepository.findById(id)
            .orElseThrow(() -> new MatchNotFoundException("Match not found: " + id));

        final var collectionOrder = collectionOrderRepository.findById(id);

        if (collectionOrder.isPresent()) {
            final var order = collectionOrder.get();
                    order.setAssignedCollector(collector);
                    order.setDueDate(dueDate);

            return new CollectionOrderDto(order.getId(), match.getName(), order.getDueDate(), order.getAssignedCollector());
        } else {
            return createNewCollectionOrder(match, dueDate, collector);
        }
    }

    private CollectionOrderDto createNewCollectionOrder(Match match, Instant dueDate, String collector) {
        final var collectionOrder = new CollectionOrder(match, dueDate, collector);
        collectionOrderRepository.save(collectionOrder);

        return new CollectionOrderDto(collectionOrder.getId(), match.getName(), collectionOrder.getDueDate(), collectionOrder.getAssignedCollector());
    }
}
