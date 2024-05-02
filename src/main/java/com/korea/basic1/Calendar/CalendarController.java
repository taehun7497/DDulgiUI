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

@RequiredArgsConstructor
@Controller
@RequestMapping("/calendar")
public class CalendarController {

    private final CalendarService calendarService;
    private final EventService eventService;

    @GetMapping("/{calendarId}")
    public String viewCalendar(Model model,
                               @PathVariable(name = "calendarId") Long calendarId
                             ) {
        Calendar calendar = this.calendarService.getcalendar(calendarId);

        model.addAttribute("calendarId", calendarId);
        return "calendarForm";
    }

    @PostMapping("/events")
    public ResponseEntity<?> createEvent(@RequestBody EventForm eventForm) {
        Event createdEvent = eventService.create(eventForm.getTitle(), eventForm.getStartDate(), eventForm.getEndDate(), eventForm.getRegistrationLink(), eventForm.getCalendar());
        if (createdEvent != null) {
            return ResponseEntity.ok(createdEvent); // 이벤트 객체를 반환
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이벤트 생성 실패");
        }
    }

}