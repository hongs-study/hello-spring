package com.example.hellospring.controller.datetime;

import com.example.hellospring.converter.KstStringToUtcDateTimeConverter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter // 데이터바인딩을위해 필수
public class DateTimeRequest {

    // 부분-방법1 - 어노테이션
    @NotNull(message = "종료일을 입력하세요.")
    @JsonDeserialize(converter = KstStringToUtcDateTimeConverter.class) // @RequestBody
    @UtcLocalDateTime // @RequestParam, @PathVariable, @ModelAttribute
    private LocalDateTime datetime; // [글로벌,부분 공통] 반드시 LocalDateTime 타입을 사용한다(yyyy-MM-dd 패턴이더라도)

    /*
    부분-방법2 - setter
    public void setDatetime(String datetime) {
        StringToUtcDateTimeConverter conve = new StringToUtcDateTimeConverter();
        this.datetime = conve.convert(datetime);
    }*/
}