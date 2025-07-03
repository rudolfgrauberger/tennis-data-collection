package xyz.grauberger.application.collection.tool.mapper;

import java.util.List;

import lombok.experimental.UtilityClass;
import xyz.grauberger.application.collection.tool.dto.EventDetailDto;
import xyz.grauberger.application.collection.tool.persistence.Event;

@UtilityClass
public class EventMapper {
    public static EventDetailDto toDto(Event event) {
        return new EventDetailDto(
            event.getId(),
            event.getType(),
            new EventDetailDto.Player(
                event.getPlayer().getId(),
                event.getPlayer().getFirstName(),
                event.getPlayer().getLastName(),
                event.getPlayer().getGender()
            )
        );
    }

    public static List<EventDetailDto> toDtoList(List<Event> events) {
        return events.stream()
            .map(EventMapper::toDto)
            .toList();
    }
}
