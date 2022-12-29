package com.example.hellospring.converter;

import com.example.hellospring.controller.datetime.UtcLocalDateTime;
import com.fasterxml.jackson.databind.util.StdConverter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

/**
 * 부분적용 (RequestParam등=@UtcLocalDateTime어노테이션 | RequestBody=@JsonSerialize어노테이션)
 * KST (String) -> UTC (LocalDateTime)
 */
@Slf4j
public class KstStringToUtcDateTimeConverter extends StdConverter<String, LocalDateTime> implements ConditionalGenericConverter {

    // 1가지 포맷만 지원한다
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    private final static ZoneId SERVER_TIMEZONE = ZoneId.of("UTC");

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return targetType.hasAnnotation(UtcLocalDateTime.class);
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(String.class, LocalDateTime.class));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        return getDateTimeFromString((String) source);
    }

    @Override
    public LocalDateTime convert(String value) {
        return getDateTimeFromString(value);
    }

    private LocalDateTime getDateTimeFromString(String source) {

        // 입력된 날짜의 타임존을 확인 후 변환한다
        try {
            ZonedDateTime requestZonedDateTime = ZonedDateTime.parse(source, DATETIME_FORMATTER);
            LocalDateTime serverDateTime = requestZonedDateTime.withZoneSameInstant(SERVER_TIMEZONE).toLocalDateTime();
            log.info(" \r\nDateTime IN >>>> requestDateTime=[TimeZone:{}, DateTime:{}]  ===>  serverDateTime=[TimeZone:{}, DateTime:{}]"
                , requestZonedDateTime.getZone(), requestZonedDateTime, SERVER_TIMEZONE, serverDateTime);
            return serverDateTime;
        } catch (NullPointerException | DateTimeParseException ignored) {
        }

        // 포맷에 맞지 않으면 Exception 발생
        throw new RuntimeException("This date-time format can't be converted. Please insert date-time format to [2022-01-01T10:15:30+09:00]");
        //return null; // null 로 반환할 수도 있다(비지니스에 따라 결정)
    }
}
