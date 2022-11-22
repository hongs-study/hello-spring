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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss+0900", timezone = "Asia/Seoul")
    private ZonedDateTime zonedDateTime = ZonedDateTime.now();

}
