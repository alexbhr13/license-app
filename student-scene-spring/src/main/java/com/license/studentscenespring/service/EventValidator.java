package com.license.studentscenespring.service;

import com.license.studentscenespring.dto.EventDTO;
import com.license.studentscenespring.model.Event;
import com.license.studentscenespring.repository.EventRepository;
import com.license.studentscenespring.security.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventValidator {

    private final JWTService jwtService;
    private final EventRepository eventRepository;

    private static final String URL_PATTERN = "^(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})([/\\w .-]*)*/?$";

    public void validate(EventDTO eventDTO) throws Exception {
        validateRequiredFields(eventDTO);
        validateDates(eventDTO);
        validateLinks(eventDTO);
    }

    public void validateEventOwner(Long eventId, String token) throws Exception {
        String email = jwtService.extractEmail(token);
        Event event = eventRepository.findEventById(eventId);
        if (!event.getAdminEmail().equals(email)) {
            throw new Exception("User is not authorized to edit this event.");
        }
    }

    private void validateRequiredFields(EventDTO eventDTO) throws Exception {
        if(eventDTO.getPhoto().isEmpty()) throw new Exception("Photo is required");
        if(eventDTO.getTitle().isEmpty()) throw new Exception("Title field is required");
        if(eventDTO.getDescription().isEmpty()) throw new Exception("Description field is required");
        if(eventDTO.getStartDate() == null ||
            eventDTO.getEndDate() == null ||
            eventDTO.getStartTime() == null ||
            eventDTO.getDuration() == null) throw new Exception("Start date, end date, and time fields are required.");
        if(eventDTO.getAddress().isEmpty()) throw new Exception("Address is required.");
    }

    private void validateDates(EventDTO eventDTO) throws Exception{
        if(eventDTO.getStartDate().isAfter(eventDTO.getEndDate())) throw new Exception("End date cannot be before start date.");
    }

    private void validateLinks(EventDTO eventDto) throws Exception {
        if (!eventDto.getEventLink().matches(URL_PATTERN) && !eventDto.getEventLink().isEmpty()) {
            throw new Exception("Event link is not a valid URL.");
        }
        if (!eventDto.getTicketLink().matches(URL_PATTERN) && !eventDto.getTicketLink().isEmpty()) {
            throw new Exception("Ticket link is not a valid URL.");
        }
    }




}
