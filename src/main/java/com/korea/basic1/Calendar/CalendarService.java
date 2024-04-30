package com.korea.basic1.Calendar;


import com.korea.basic1.Event.Event;
import com.korea.basic1.Event.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CalendarService {
    @Autowired
    private CalendarRepository calendarRepository;

    @Autowired
    private EventRepository eventRepository;

    public Calendar createCalendar(List<Event> events) {
        Calendar calendar = new Calendar();
        calendar.setCreateDate(LocalDateTime.now());
        calendar.setModifyDate(LocalDateTime.now());
        calendar.setEventList(events);
        return calendarRepository.save(calendar);
    }

    public Event createEvent(String title, LocalDateTime startDate, LocalDateTime endDate, Calendar calendar) {
        Event event = new Event();
        event.setTitle(title);
        event.setStartDate(startDate);
        event.setEndDate(endDate);
        event.setCalendar(calendar);
        return eventRepository.save(event);
    }

    public Calendar findById(Long id) {
        Optional<Calendar> calendar = calendarRepository.findById(id);
        if (calendar.isPresent()) {
            return calendar.get();
        } else {
            // 예외를 던질 수 있습니다. 예를 들어 CalendarNotFoundException 등
            throw new RuntimeException("Calendar not found with id: " + id);
        }
    }

}