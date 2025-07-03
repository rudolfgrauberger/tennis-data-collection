package xyz.grauberger.application.collection.tool;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import xyz.grauberger.application.collection.exceptions.EventNotFoundException;
import xyz.grauberger.application.collection.exceptions.OrderAlreadyCompletedException;
import xyz.grauberger.application.collection.exceptions.OrderNotFoundException;
import xyz.grauberger.application.collection.management.entities.CollectionOrder;
import xyz.grauberger.application.collection.management.entities.MatchResult;
import xyz.grauberger.application.collection.management.repositories.CollectionOrderRepository;
import xyz.grauberger.application.collection.management.repositories.MatchResultRepository;
import xyz.grauberger.application.collection.tool.domain.KPI;
import xyz.grauberger.application.collection.tool.domain.OrderStatisticsDto;
import xyz.grauberger.application.collection.tool.domain.ResultService;
import xyz.grauberger.application.collection.tool.dto.EventDetailDto;
import xyz.grauberger.application.collection.tool.mapper.EventMapper;
import xyz.grauberger.application.collection.tool.persistence.Event;
import xyz.grauberger.application.collection.tool.persistence.EventRepository;
import xyz.grauberger.application.collection.tool.persistence.EventType;
import xyz.grauberger.application.collection.tool.persistence.PlayerKpi;
import xyz.grauberger.application.collection.tool.persistence.PlayerKpiRepository;
import xyz.grauberger.application.masterdata.entities.player.Player;
import xyz.grauberger.application.masterdata.exceptions.MatchNotFoundException;
import xyz.grauberger.application.masterdata.exceptions.PlayerNotFoundException;
import xyz.grauberger.application.masterdata.repositories.MatchRepository;
import xyz.grauberger.application.masterdata.repositories.PlayerRepository;
import xyz.grauberger.application.masterdata.services.MatchVideoService;

@Service
@RequiredArgsConstructor
public class ToolService {
    private final EventRepository eventRepository;
    private final PlayerRepository playerRepository;
    private final CollectionOrderRepository collectionOrderRepository;
    private final MatchRepository matchRepository;
    private final MatchResultRepository matchResultRepository;
    private final ResultService resultService;
    private final PlayerKpiRepository playerKpisRepository;


    private final MatchVideoService matchVideoService;

    @Transactional
    public EventDetailDto createEvent(long orderId, EventType type, long playerId) {
        final var collectionOrder = getCollectionOrder(orderId);

        if (collectionOrder.isCollected()) {
            throw new OrderAlreadyCompletedException("Cannot create event for completed collection order");
        }

        final var player = playerRepository.findById(playerId).orElseThrow(() -> new PlayerNotFoundException("Player not found: " + playerId));

        final var event = new Event(type, player, collectionOrder);
        eventRepository.save(event);

        return EventMapper.toDto(event);
    }

    @Transactional
    public EventDetailDto updateEvent(long orderId, long id, EventType type, long playerId) {
        verifyCollectionState(orderId);

        final var event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event not found: " + id));

        final var player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("Player not found: " + playerId));

        event.setType(type);
        event.setPlayer(player);

        return EventMapper.toDto(event);
    }

    @Transactional(readOnly = true)
    public List<EventDetailDto> getEvents(long orderId) {
        final var events =  eventRepository.findByCollectionOrderId(orderId);

        return EventMapper.toDtoList(events);
    }

    @Transactional
    public void deleteEvent(long orderId, long id) {
        verifyCollectionState(orderId);

        eventRepository.deleteById(id);
    }

    @Transactional
    public OrderStatisticsDto complete(long orderId) {
        final var collectionOrder = getCollectionOrder(orderId);

        final var events = eventRepository.findByCollectionOrderId(orderId);

        final var matchResult = resultService.computeResults(events);

        final var match = matchRepository.findById(orderId)
                .orElseThrow(() -> new MatchNotFoundException("Match not found: " + orderId));

        final Map<Long, Player> players = match.getPlayers().stream().collect(Collectors.toMap(Player::getId, player -> player));

        final var playerKpis = matchResult.getAllKpis().stream()
            .map(kpi -> new PlayerKpi(kpi, players.get(kpi.playerId()), match))
            .toList();

        playerKpisRepository.saveAll(playerKpis);

        final var result = new MatchResult(orderId, matchResult.asReadableNotion());
        matchResultRepository.save(result);

        collectionOrder.markAsCollected();

        return new OrderStatisticsDto(
            events.size(),
            matchResult.getNumberOfGames(),
            matchResult.getCount(KPI.KpiName.SERVES),
            matchResult.getCount(KPI.KpiName.WINS));
    }

    private void verifyCollectionState(long orderId) {
        final var collectionOrder = getCollectionOrder(orderId);

        if (collectionOrder.isCollected()) {
            throw new OrderAlreadyCompletedException("Cannot modify completed collection order");
        }
    }

    private CollectionOrder getCollectionOrder(long orderId) {
        return collectionOrderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Collection order not found: " + orderId));
    }

    public String getVideo(long orderId) {
        return matchVideoService.getVideoUrl(orderId);
    }
}
