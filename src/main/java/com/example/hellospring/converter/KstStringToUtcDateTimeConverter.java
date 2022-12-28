package com.example.hellospring.converter;

import com.example.hellospring.controller.datetime.UtcLocalDateTime;
import com.example.hellospring.utils.DateTimeUtil;
import com.fasterxml.jackson.databind.util.StdConverter;
import java.time.LocalDateTime;
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
 * KST (String) -> UTC (LocalDateTime)
 */
@Slf4j
public class KstStringToUtcDateTimeConverter extends StdConverter<String, LocalDateTime> implements ConditionalGenericConverter {

    final DateTimeFormatter YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    Set<DateTimeFormatter> patterns = new HashSet<>(Arrays.asList(
        DateTimeFormatter.ISO_DATE,
        DateTimeFormatter.ISO_DATE_TIME,
        YYYY_MM_DD,
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
            throw new RuntimeException("에러");
        }

        // patterns 를 돌면서 날짜타입으로 정상 전환되면 반환한다.
        for (DateTimeFormatter pattern : patterns) {
            String strDateTime = source;

            try {
                LocalDateTime kstDateTime = LocalDateTime.parse(strDateTime, pattern);
                ZonedDateTime utcZonedDateTime = DateTimeUtil.utcFromKst(kstDateTime);
                LocalDateTime utcDateTime = utcZonedDateTime.toLocalDateTime();
                log.info(" KST={}  ===>  UTC={}", kstDateTime, utcDateTime);
                return utcDateTime;
            } catch (NullPointerException | DateTimeParseException ignored) {}
        }

        throw new RuntimeException("에러");
    }
}
