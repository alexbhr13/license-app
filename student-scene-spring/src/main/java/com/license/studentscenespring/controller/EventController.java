package com.license.studentscenespring.controller;

import com.license.studentscenespring.dto.EventDTO;
import com.license.studentscenespring.model.Event;
import com.license.studentscenespring.service.EventService;
import com.license.studentscenespring.service.IEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin
@RequestMapping(path = "api/v1/events")
@RestController
@RequiredArgsConstructor
public class EventController {


    private final IEventService eventService;
    @PostMapping("/create")
    public ResponseEntity<String> createEvent(@RequestBody EventDTO eventDTO, @RequestHeader("Authorization") String token) {
        try {
            eventService.createEvent(eventDTO, token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }return ResponseEntity.status(HttpStatus.OK).body("Event created");

    }

    @PutMapping("/{eventId}")
    public ResponseEntity<String> updateEvent(
            @PathVariable Long eventId,
            @RequestBody EventDTO eventDTO,
            @RequestHeader("Authorization") String token
    ) {
        try{
            eventService.updateEvent(eventId,eventDTO,token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Event updated");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEventByID(@PathVariable Long id) {
        try{
            EventDTO eventDTO = eventService.getEventByID(id);
            return new ResponseEntity<>(eventDTO,HttpStatus.OK);
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> getEvents(
            @RequestParam(required = false, value = "start_date")LocalDateTime startDate,
            @RequestParam(required = false, value = "end_date") LocalDateTime endDate,
            @RequestParam(required = false, value = "tags") String tags,
            @RequestParam(required = false, value = "event_status") String event_status,
            @RequestParam(required = false, value = "search_input") String search_input,
            @RequestParam(required = false, value = "my_events") Boolean my_events,
            @RequestParam(value = "offset") Integer offset,
            @RequestParam(value = "page_size") Integer page_size,
            @RequestHeader("Authorization") String token
            ){
        List<EventDTO> filteredEvents = eventService.getEvents(startDate,endDate,tags,event_status
                ,search_input,my_events,offset,page_size,token);
        return new ResponseEntity<>(filteredEvents,HttpStatus.OK);
    }



}
