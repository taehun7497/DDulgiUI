package com.korea.basic1.Calendar;


import com.korea.basic1.Event.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class CalendarController {
    @Autowired
    private CalendarService calendarService;

    @PostMapping
    public ResponseEntity<Calendar> createCalendar(@RequestBody List<Event> events) {
        return ResponseEntity.ok(calendarService.createCalendar(events));
    }

    @GetMapping("/calendar")
    public String viewCalendar() {
        return "CalendarForm"; // 뷰의 이름을 문자열로 반환
    }

    @PostMapping("/calendar")
    public ResponseEntity<Event> createEvent(
            @RequestParam String title,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam Long calendarId) {
        Calendar calendar = calendarService.findById(calendarId);
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);
        return ResponseEntity.ok(calendarService.createEvent(title, start, end, calendar));
    }
}