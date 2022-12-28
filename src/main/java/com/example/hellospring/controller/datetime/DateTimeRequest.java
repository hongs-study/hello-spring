package com.example.hellospring.controller.datetime;

import com.example.hellospring.converter.KstStringToUtcDateTimeConverter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter // 데이터바인딩을위해 필수
public class DateTimeRequest {

    // 방법1 - 어노테이션
    //@JsonDeserialize(converter = KstStringToUtcDateTimeConverter.class) // @RequestBody
    @UtcLocalDateTime // @RequestParam, @PathVariable, @ModelAttribute
    private LocalDateTime datetime; // 반드시 LocalDateTime 타입을 사용한다(yyyy-MM-dd 패턴이더라도)

    /*
    방법2 - setter
    public void setDatetime(String datetime) {
        StringToUtcDateTimeConverter conve = new StringToUtcDateTimeConverter();
        this.datetime = conve.convert(datetime);
    }*/
}