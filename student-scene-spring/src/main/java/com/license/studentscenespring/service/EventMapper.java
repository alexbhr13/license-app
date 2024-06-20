package com.license.studentscenespring.service;

import com.license.studentscenespring.dto.EventDTO;
import com.license.studentscenespring.model.Event;
import com.license.studentscenespring.util.EventStatus;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class EventMapper {

    public EventDTO toDTO(Event event) {
        return EventDTO.builder()
                .id(event.getId())
                .photo(event.getPhoto())
                .title(event.getTitle())
                .description(event.getDescription())
                .startDate(event.getStartDateTime().toLocalDate())
                .endDate(event.getEndDateTime().toLocalDate())
                .startTime(event.getStartDateTime().toLocalTime())
                .duration(Duration.between(event.getEndDateTime().toLocalTime(), (event.getStartDateTime().toLocalTime())))
                .address(event.getAddress())
                .eventLink(event.getEventLink())
                .ticketLink(event.getTicketLink())
                .adminEmail(event.getAdminEmail())
                .tagName(event.getTagName())
                .eventStatus(event.getEventStatus())
                .build();
    }

    public Event fromDTO(EventDTO eventDTO) {
        Event.EventBuilder eventBuilder = Event.builder()
                .id(eventDTO.getId())
                .photo(eventDTO.getPhoto())
                .title(eventDTO.getTitle())
                .description(eventDTO.getDescription())
                .address(eventDTO.getAddress())
                .eventLink(eventDTO.getEventLink())
                .ticketLink(eventDTO.getTicketLink())
                .adminEmail(eventDTO.getAdminEmail())
                .tagName(eventDTO.getTagName());

        if(eventDTO.getStartDate() != null) {
            if(eventDTO.getStartTime() != null) {
                eventBuilder.startDateTime(LocalDateTime.of(eventDTO.getStartDate(), eventDTO.getStartTime()));
            } else {
                eventBuilder.startDateTime(LocalDateTime.of(eventDTO.getStartDate(), LocalTime.of(12,0)));
            }
        } else {
            eventBuilder.startDateTime(null);
        }

        if(eventDTO.getEndDate() != null) {
            if(eventDTO.getStartTime() != null && eventDTO.getDuration() !=null) {
                eventBuilder.endDateTime(LocalDateTime.of(eventDTO.getEndDate(), eventDTO.getStartTime().plus(eventDTO.getDuration())));
            } else {
                eventBuilder.endDateTime(LocalDateTime.of(eventDTO.getEndDate(), LocalTime.of(12,0)));
            }
        } else {
            eventBuilder.endDateTime(null);
        }

        if (eventDTO.getEventStatus() != null) {
            eventBuilder.eventStatus(eventDTO.getEventStatus());
        } else {
            eventBuilder.eventStatus(EventStatus.DRAFT);
        }

        return eventBuilder.build();


    }


}
