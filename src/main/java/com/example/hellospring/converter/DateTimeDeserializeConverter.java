package com.example.hellospring.converter;

import com.fasterxml.jackson.databind.util.StdConverter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.springframework.util.StringUtils;

@Deprecated
public class DateTimeDeserializeConverter extends StdConverter<String, LocalDateTime> {
    @Override
    public LocalDateTime convert(final String value) {
        return LocalDateTime.now();
        /*Set<DateTimeFormatter> patterns = new HashSet<>(Arrays.asList(
            DateTimeFormatter.ISO_DATE_TIME, // "yyyy-MM-ddTHH:mm:ss.sss" 패턴
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        ));

        // null 처리 - null로 들어오면 그냥 null을 반환하겠습니다.
        if (value == null) {
            return null;
        } else if (StringUtils.isEmpty(value.trim())) {
            return null;
        }

        // patterns 를 돌면서 날짜타입으로 정상 전환되면 반환한다.
        for (DateTimeFormatter pattern : patterns) {
            try {
                return LocalDateTime.parse(value, pattern);
            } catch (NullPointerException | DateTimeParseException ignored) {}
        }

        throw new RuntimeException("에러");*/
    }
}