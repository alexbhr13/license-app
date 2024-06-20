package com.license.studentscenespring.dto;

import com.license.studentscenespring.util.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {
    private Long id;
    private String photo;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private Duration duration;
    private String address;
    private String eventLink;
    private String ticketLink;
    private String adminEmail;
    private String tagName;
    private EventStatus eventStatus;
}
