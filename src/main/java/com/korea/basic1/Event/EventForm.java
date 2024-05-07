package com.korea.basic1.Event;

import com.korea.basic1.Calendar.Calendar;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventForm {
    @NotEmpty(message = "내용은 필수항목입니다.")
    private String title;

    @NotEmpty(message = "시작 날짜는 필수항목입니다.")
    private LocalDateTime startDate;  // LocalDateTime 타입 사용

    @NotEmpty(message = "종료 날짜는 필수항목입니다.")
    private LocalDateTime endDate;    // LocalDateTime 타입 사용

    private String registrationLink;

    private Long calendar_id;  // Calendar 객체 대신 ID 사용

}
