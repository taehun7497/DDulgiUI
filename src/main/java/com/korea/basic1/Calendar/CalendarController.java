package com.korea.basic1.Calendar;


import com.korea.basic1.Event.Event;
import com.korea.basic1.Event.EventForm;
import com.korea.basic1.Event.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String viewCalendar(Model model, @PathVariable(name = "calendarId") Long calendarId) {
        List<Event> events = this.eventService.findByCalendarId(calendarId);

        model.addAttribute("calendarId", calendarId);
        model.addAttribute("events", events); // 이벤트 리스트를 모델에 추가

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