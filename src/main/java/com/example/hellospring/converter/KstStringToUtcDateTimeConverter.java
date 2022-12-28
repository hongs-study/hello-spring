package com.example.hellospring.converter;

import com.example.hellospring.controller.datetime.UtcLocalDateTime;
import com.example.hellospring.utils.DateTimeUtil;
import com.fasterxml.jackson.databind.util.StdConverter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

/**
 * 부분적용 (RequestParam등=@UtcLocalDateTime어노테이션 | RequestBody=@JsonSerialize어노테이션)
 * KST (String) -> UTC (LocalDateTime)
 */
@Slf4j
public class KstStringToUtcDateTimeConverter extends StdConverter<String, LocalDateTime> implements ConditionalGenericConverter {

    private static final String dateFormat = "yyyy-MM-dd";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(dateFormat);

    Set<DateTimeFormatter> dateTimeFormatters = new HashSet<>(Arrays.asList(
        DateTimeFormatter.ISO_DATE_TIME,
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    ));

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

        if (StringUtils.isEmpty(source.trim())) {
            return null;
        }

        for (DateTimeFormatter pattern : dateTimeFormatters) {
            try {
                LocalDateTime kstDateTime = LocalDateTime.parse(source, pattern);
                ZonedDateTime utcZonedDateTime = DateTimeUtil.utcFromKst(kstDateTime);
                LocalDateTime utcDateTime = utcZonedDateTime.toLocalDateTime();
                log.info(" KST={}  ===>  UTC={}", kstDateTime, utcDateTime);
                return utcDateTime;
            } catch (NullPointerException | DateTimeParseException ignored) {
            }
        }

        try {
            LocalDate parsedDate = LocalDate.parse(source, DATE_FORMATTER);
            LocalDateTime kstDateTime = LocalDateTime.of(parsedDate, LocalTime.MIN);
            ZonedDateTime utcZonedDateTime = DateTimeUtil.utcFromKst(kstDateTime);
            LocalDateTime utcDateTime = utcZonedDateTime.toLocalDateTime();
            log.info(" KST={}  ===>  UTC={}", kstDateTime, utcDateTime);
            return utcDateTime;
        } catch (NullPointerException | DateTimeParseException ignored) {
        }

        return null;
    }
}
