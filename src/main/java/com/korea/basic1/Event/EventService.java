package com.korea.basic1.Event;

import com.korea.basic1.Calendar.Calendar;
import com.korea.basic1.Calendar.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EventService {

    private final EventRepository eventRepository;
    private final CalendarService calendarService;

    public List<Event> getEventsForMonth(List<Event> allEvents, int targetMonth) {
        // 주어진 이벤트 목록(allEvents)을 스트림으로 변환
        return allEvents.stream()
                // 각 이벤트 객체에 대해 시작일을 가져와서 그 달의 값이 타겟 달과 같은지 확인하는 필터링 조건을 적용
                .filter(event -> {
                    LocalDateTime startDate = event.getStartDate();
                    return startDate != null && startDate.getMonthValue() == targetMonth;
                })
                // 필터링된 이벤트를 리스트로 수집하여 반환
                .collect(Collectors.toList());
    }

    public Event create(String title, LocalDateTime startDate, LocalDateTime endDate, String link, Long calendarId) {
        Event e = new Event();
        e.setTitle(title);
        e.setStartDate(startDate);
        e.setEndDate(endDate);
        e.setRegistrationLink(link);
        Calendar calendar = calendarService.getcalendar(calendarId);
        e.setCalendar(calendar);
        return eventRepository.save(e); // 저장된 이벤트 객체 반환
    }

    public void modify(Event event, String title, LocalDateTime startDate, LocalDateTime EndDate, String link) {
        event.setTitle(title);
        event.setStartDate(startDate);
        event.setEndDate(EndDate);
        event.setRegistrationLink(link);
        this.eventRepository.save(event);
    }

    public void delete(Event event) {
        this.eventRepository.delete(event);
    }

    public List<Event> findByCalendarId(Long calendarId) {
        return eventRepository.findByCalendarId(calendarId);
    }

//    public Event update(Long eventId, String title, LocalDateTime startDate, LocalDateTime endDate,
//                        String registrationLink, Long calendarId) {
//        Optional<Event> optionalEvent = eventRepository.findById(eventId);
//        if (optionalEvent.isPresent()) {
//            Event event = optionalEvent.get();
//            event.setTitle(title);
//            event.setStartDate(startDate);
//            event.setEndDate(endDate);
//            event.setRegistrationLink(registrationLink);
//            // CalendarId도 업데이트가 필요하면 업데이트
//            event.getCalendar(calendarId);
//            return eventRepository.save(event);
//        } else {
//            return null;
//        }
//    }
}
