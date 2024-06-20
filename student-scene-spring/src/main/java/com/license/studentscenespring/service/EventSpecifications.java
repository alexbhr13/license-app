package com.license.studentscenespring.service;

import com.license.studentscenespring.model.Event;
import com.license.studentscenespring.util.EventStatus;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;

public class EventSpecifications {

    public static Specification<Event> startDateTimeGreaterThanOrEqual(LocalDateTime startDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("startDateTime"), startDate);
    }

    public static Specification<Event> endDateTimeLessThanOrEqual(LocalDateTime endDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("endDateTime"), endDate);
    }

    public static Specification<Event> hasTags(List<String> tags) {
        return (root, query, criteriaBuilder) ->
                root.get("tagName").in(tags);
    }

    public static Specification<Event> hasEventTypes(List<EventStatus> eventStatus) {
        return (root, query, criteriaBuilder) ->
                root.get("eventStatus").in(eventStatus);
    }

    public static Specification<Event> containsText(String searchInput) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.or(
                        criteriaBuilder.like(root.get("title"), "%" + searchInput + "%"),
                        criteriaBuilder.like(root.get("description"), "%" + searchInput + "%"),
                        criteriaBuilder.like(root.get("address"), "%" + searchInput + "%")
                );
    }

    public static Specification<Event> isMyEvent(String userEmail) {
        return (root, query, criteriaBuilder) -> (
                criteriaBuilder.equal(root.get("adminEmail"), userEmail)
        );
    }

}
