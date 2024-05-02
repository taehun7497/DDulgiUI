package com.korea.basic1.Calendar;

import com.korea.basic1.Event.Event;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "calendar", cascade = CascadeType.REMOVE)
    private List<Event> eventList;

    private LocalDateTime createDate;
}
