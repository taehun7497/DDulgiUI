package com.korea.basic1.Event;


import com.korea.basic1.Calendar.Calendar;
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

    @DateTimeFormat(pattern = "yyyy-MM-dd HH")
    private LocalDateTime startDate;  // LocalDateTime 타입 사용

    @DateTimeFormat(pattern = "yyyy-MM-dd HH")
    private LocalDateTime endDate;    // LocalDateTime 타입 사용

    private String registrationLink;

    @ManyToOne
    private Calendar calendar;
}
