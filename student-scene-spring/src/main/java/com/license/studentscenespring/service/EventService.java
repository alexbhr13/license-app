package com.license.studentscenespring.service;


import com.license.studentscenespring.dto.EventDTO;
import com.license.studentscenespring.model.Event;
import com.license.studentscenespring.repository.EventRepository;
import com.license.studentscenespring.security.JWTService;
import com.license.studentscenespring.util.EventStatus;
import com.license.studentscenespring.util.QueryParser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService implements IEventService{

    private final JWTService jwtService;
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;
    private final EventValidator eventValidator;

    public void createEvent(EventDTO eventDTO, String token) throws Exception {
        String adminEmail = jwtService.extractEmail(token);
        if(!jwtService.extractIsAdmin(token)) throw new Exception("Unauthorized");
        try{
            jwtService.getAuthentication(token);
            if(eventDTO.getEventStatus() == EventStatus.DRAFT) {
                Event event = eventMapper.fromDTO(eventDTO);
                event.setAdminEmail(adminEmail);
                eventRepository.save(event);
                return;
            }
            eventValidator.validate(eventDTO);
            Event event = eventMapper.fromDTO(eventDTO);
            event.setAdminEmail(adminEmail);
            eventRepository.save(event);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void updateEvent(Long eventID, EventDTO eventDTO, String token)  throws Exception{
        eventValidator.validateEventOwner(eventID,token);
        eventValidator.validate(eventDTO);

        Event existingEvent = eventRepository.findById(eventID)
                .orElseThrow(()->new Exception("Event not found"));
        eventRepository.saveAndFlush(existingEvent);
    }

    public EventDTO getEventByID(Long id) throws Exception {
        return eventRepository.findById(id).map(eventMapper::toDTO)
                .orElseThrow(()->new Exception("Event not found"));
    }

    public List<EventDTO> getEvents(
            LocalDateTime startDate, LocalDateTime endDate, String tags,
            String eventStatus, String searchInput, Boolean myEvents,
            Integer offset, Integer pageSize, String token
    ) {
        Specification<Event> specification = Specification.where(null);
        if(startDate!= null) specification =
                specification.and(EventSpecifications.startDateTimeGreaterThanOrEqual(startDate));
        if(endDate != null) specification =
                specification.and(EventSpecifications.endDateTimeLessThanOrEqual(endDate));
        if(tags != null && !tags.isEmpty()) {
            List<String> tagsList = QueryParser.parse(tags);
            specification = specification.and(EventSpecifications.hasTags(tagsList));
        }
        if(eventStatus != null && !eventStatus.isEmpty()) {
            List<EventStatus> eventStatusList = QueryParser.parse(eventStatus).stream()
                    .map(EventStatus::valueOf)
                    .toList();
            specification = specification.and(EventSpecifications.hasEventTypes(eventStatusList));
        }
        if(searchInput != null && !searchInput.isEmpty()) specification =
                specification.and(EventSpecifications.containsText(searchInput));
        if(myEvents != null && myEvents) {
            String tokenWithoutBearer = token.substring(7);
            if(jwtService.extractIsAdmin(tokenWithoutBearer)) {
                String userEmail = jwtService.extractEmail(tokenWithoutBearer);
                specification = specification.and(EventSpecifications.isMyEvent(userEmail));
            }
        }

        PageRequest pageRequest = PageRequest.of(offset/pageSize, pageSize);

        Page<Event> eventPage = eventRepository.findAll(specification,pageRequest);

        return eventPage.getContent()
                .stream()
                .map(eventMapper::toDTO)
                .collect(Collectors.toList());
    }


}
