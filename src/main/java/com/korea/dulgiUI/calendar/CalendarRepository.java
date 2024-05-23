package com.korea.dulgiUI.calendar;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar,Long> {
    List<Calendar> findByUsername(String username);
}
