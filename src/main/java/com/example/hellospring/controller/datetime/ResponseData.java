package com.example.hellospring.controller.datetime;

import com.example.hellospring.converter.UtcDateTimeToKstStringConverter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDateTime;
import lombok.Setter;

@Setter
public class ResponseData {

    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul") - 방법1
    //@JsonSerialize(converter = UtcDateTimeToKstStringConverter.class) // 방법2
    public LocalDateTime responseDatetime;

}
