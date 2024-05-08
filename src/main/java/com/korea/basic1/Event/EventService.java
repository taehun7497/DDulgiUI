package com.korea.basic1.Event;

import com.korea.basic1.Calendar.Calendar;
import com.korea.basic1.Calendar.CalendarService;
import jakarta.persistence.EntityNotFoundException;
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

    public Event modify(Long eventId, String title, LocalDateTime startDate, LocalDateTime endDate, String registrationLink, Long calendarId) {
        // 1. 주어진 이벤트 ID를 사용하여 데이터베이스에서 해당 이벤트를 조회합니다.
        Event event = eventRepository.findById(eventId).orElse(null);

        // 2. 조회된 이벤트가 존재하는지 확인합니다.
        if (event == null) {
            // 이벤트가 존재하지 않으면 예외를 발생시킵니다.
            throw new EntityNotFoundException("Event not found with ID: " + eventId);
        }

        // 3. 클라이언트가 전송한 수정된 정보를 사용하여 이벤트를 업데이트합니다.
        // 주로 이벤트의 제목, 시작일, 종료일, 등록 링크 등의 정보가 업데이트됩니다.
        event.setTitle(title);
        event.setStartDate(startDate);
        event.setEndDate(endDate);
        event.setRegistrationLink(registrationLink);
        // 다른 필드도 필요에 따라 업데이트할 수 있습니다.

        // 4. 업데이트된 이벤트를 데이터베이스에 저장하고, 업데이트된 이벤트를 반환합니다.
        return eventRepository.save(event);
    }

    public void delete(Event event) {
        this.eventRepository.delete(event);
    }

    public List<Event> findByCalendarId(Long calendarId) {
        return eventRepository.findByCalendarId(calendarId);
    }

    public Event findById(Long eventId) {
        // 이벤트 ID를 사용하여 데이터베이스에서 해당 이벤트를 조회합니다.
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with ID: " + eventId));
    }
}
