package com.korea.basic1.Event;

import com.korea.basic1.Calendar.Calendar;
import com.korea.basic1.Calendar.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class EventService {

    private final EventRepository eventRepository;
    private final CalendarService calendarService;

    public Event create(String title, LocalDateTime startDate, LocalDateTime endDate, String link, Long calendarId){
        Event e = new Event();
        e.setTitle(title);
        e.setStartDate(startDate);
        e.setEndDate(endDate);
        e.setRegistrationLink(link);
        Calendar calendar = calendarService.getcalendar(calendarId);
        e.setCalendar(calendar);
        return eventRepository.save(e); // 저장된 이벤트 객체 반환
    }

    public void modify(Event event,String title, LocalDateTime startDate, LocalDateTime EndDate, String link){
        event.setTitle(title);
        event.setStartDate(startDate);
        event.setEndDate(EndDate);
        event.setRegistrationLink(link);
        this.eventRepository.save(event);
    }

    public void delete(Event event){
        this.eventRepository.delete(event);
    }
}
