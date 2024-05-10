package com.korea.dulgiUI.Event;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {
    List<Event> findByCalendarId(Long calendarId);
}
