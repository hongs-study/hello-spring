package com.example.hellospring.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Hello {

    private String name;
    private LocalDate date = LocalDate.now();
    private LocalDateTime dateTime = LocalDateTime.now();

    // 방법1 - 필드 개별 적용
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private ZonedDateTime zoneTime = ZonedDateTime.now();

}
