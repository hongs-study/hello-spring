package com.example.hellospring.converter;

import com.example.hellospring.utils.DateTimeUtil;
import com.fasterxml.jackson.databind.util.StdConverter;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;

/**
 * UTC (LocalDateTime) -> KST (String)
 */
@Slf4j
public class UtcDateTimeToKstSerializer extends StdConverter<LocalDateTime, String> {
    @Override
    public String convert(LocalDateTime value) {
        ZonedDateTime zonedDateTime = DateTimeUtil.kstFromUtc(value);
        String kstString = zonedDateTime.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        log.debug(" UTC={}  ===>  KST={}", value, kstString);
        return kstString;
    }
}
