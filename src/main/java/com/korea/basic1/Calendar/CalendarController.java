package com.korea.basic1.Calendar;


import com.korea.basic1.Event.Event;
import com.korea.basic1.Event.EventForm;
import com.korea.basic1.Event.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequiredArgsConstructor
@Controller
@RequestMapping("/calendar")
public class CalendarController {

    private final CalendarService calendarService;
    private final EventService eventService;



    @GetMapping("/{calendarId}")
    public String viewCalendar(Model model, @PathVariable(name = "calendarId") String calendarId,
                               @RequestParam(name = "targetMonth", required = false, defaultValue = "0") int targetMonth) {
        Long parsedCalendarId;
        try {
            parsedCalendarId = Long.parseLong(calendarId);
        } catch (NumberFormatException e) {
            // 예외 처리
            return "errorPage"; // 오류가 발생하면 적절한 에러 페이지로 리다이렉트합니다.
        }

        List<Event> events = this.eventService.findByCalendarId(parsedCalendarId);

        // targetMonth가 0 이하면 현재 월의 값을 사용하여 이벤트 목록을 가져옴
        if (targetMonth <= 0) {
            LocalDateTime now = LocalDateTime.now();
            targetMonth = now.getMonthValue();
        }

        int prevMonth = targetMonth - 1;
        int nextMonth = targetMonth + 1;

        List<Event> eventsForMonth = this.eventService.getEventsForMonth(events, targetMonth);

        model.addAttribute("targetMonth", targetMonth);
        model.addAttribute("prevMonth", prevMonth);
        model.addAttribute("nextMonth", nextMonth);
        model.addAttribute("calendarId", parsedCalendarId);
        model.addAttribute("eventsForMonth", eventsForMonth); // 이벤트 목록을 모델에 추가

        return "calendarForm";
    }

    @GetMapping("/{calendarId}/events")
    public ResponseEntity<?> getEventsByCalendarId(@PathVariable Long calendarId) {
        List<Event> events = eventService.findByCalendarId(calendarId);
        if (events != null && !events.isEmpty()) {
            return ResponseEntity.ok(events);
        } else {
            // JSON 형태의 에러 메시지 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"No events found for this calendar.\"}");
        }
    }

    @PostMapping("/events")
    public ResponseEntity<?> createEvent(@RequestBody EventForm eventForm) {
        try {
            Event createdEvent = eventService.create(eventForm.getTitle(), eventForm.getStartDate(),
                    eventForm.getEndDate(), eventForm.getRegistrationLink(), eventForm.getCalendar_id());
            if (createdEvent != null) {
                return ResponseEntity.ok(createdEvent);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Invalid event data provided.\"}");
            }
        } catch (Exception e) {
            // 서버 내부 오류를 로깅
            Logger.getLogger(CalendarController.class.getName()).log(Level.SEVERE, "Internal server error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\":\"Internal server error occurred.\"}");
        }
    }

}