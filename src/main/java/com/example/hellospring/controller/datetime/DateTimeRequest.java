package com.example.hellospring.controller.datetime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class DateTimeRequest {

    //@UtcLocalDateTime
    //@JsonDeserialize(converter = DateTimeDeserializeConverter.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime datetime;

    public void setDatetime(String datetime) {
        this.datetime = LocalDateTime.now();
    }
}