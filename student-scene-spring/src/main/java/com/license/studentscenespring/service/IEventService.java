package com.license.studentscenespring.service;

import com.license.studentscenespring.dto.EventDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface IEventService {

    void createEvent(EventDTO eventDTO, String token) throws Exception;

    void updateEvent(Long eventID, EventDTO eventDTO, String token)  throws Exception;
    EventDTO getEventByID(Long id) throws Exception;

    List<EventDTO> getEvents(
            LocalDateTime startDate, LocalDateTime endDate, String tags,
            String eventStatus, String searchInput, Boolean myEvents,
            Integer offset, Integer pageSize, String token
    );
}
