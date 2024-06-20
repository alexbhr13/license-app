package com.license.studentscenespring.repository;

import com.license.studentscenespring.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event,Long>, JpaSpecificationExecutor<Event> {
    Event findEventById(Long id);
}
