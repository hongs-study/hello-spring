package com.example.hellospring.converter;

import com.fasterxml.jackson.databind.util.StdConverter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;

/**
 * UTC (LocalDateTime) -> KST (String)
 */
@Slf4j
public class UtcDateTimeToKstSerializer extends StdConverter<LocalDateTime, String> {

    // 1가지 포맷만 지원한다
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    private final static ZoneId SERVER_TIMEZONE = ZoneId.of("UTC");
    private final static ZoneId KST_TIMEZONE = ZoneId.of("Asia/Seoul");

    @Override
    public String convert(LocalDateTime serverDateTime) {
        // 응답 날짜 포맷, 타임존을 고정한다
        ZonedDateTime serverZonedDateTime = ZonedDateTime.of(serverDateTime, SERVER_TIMEZONE);
        String kstString = serverZonedDateTime.withZoneSameInstant(KST_TIMEZONE).format(DATETIME_FORMATTER);
        log.info(" \r\nDateTime OUT >>>> serverDateTime=[TimeZone:{}, DateTime:{}]  ===>  requestDateTime=[TimeZone:{}, DateTime:{}]"
            , SERVER_TIMEZONE, serverZonedDateTime, KST_TIMEZONE, kstString);
        return kstString;
    }
}
