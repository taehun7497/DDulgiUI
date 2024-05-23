package com.korea.dulgiUI.Event;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.korea.dulgiUI.calendar.Calendar;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endDate;

    private String registrationLink;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Calendar calendar;
}
