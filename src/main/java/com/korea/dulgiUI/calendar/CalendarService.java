package com.korea.dulgiUI.calendar;

import com.korea.dulgiUI.Event.Event;
import com.korea.dulgiUI.Event.EventRepository;
import com.korea.dulgiUI.error.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalendarService {
//
    private final CalendarRepository calendarRepository;
    private final EventRepository eventRepository;

    public Calendar createCalendar() {
        Calendar c = new Calendar();
        return calendarRepository.save(c);
    }

    public void addEvent(Long calendarId, Long eventId){
        Optional<Calendar> calendar = calendarRepository.findById(calendarId);
        Optional<Event> event = eventRepository.findById(eventId);

        if(calendar.isPresent() && event.isPresent()){
            Calendar targetCalendar = calendar.get();
            Event targetEvent = event.get();

            targetEvent.setCalendar(targetCalendar);
            eventRepository.save(targetEvent);
        }
        else{
            throw new DataNotFoundException("달력이나 일정을 찾을 수 없음");
        }
    }

    public Calendar getcalendar(Long id){
        Optional<Calendar> calendar = this.calendarRepository.findById(id);
        if (calendar.isPresent()) {
            Calendar currentCalendar = calendar.get();
            return calendar.get();
        } else {
            throw new DataNotFoundException("달력을 찾을 수 없습니다.");
        }
    }
}