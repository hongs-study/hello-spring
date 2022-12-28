package com.example.hellospring.converter;

import com.example.hellospring.utils.DateTimeUtil;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class KstStringToUtcDateTimeGlobalConverter implements Converter<String, LocalDateTime> {

    private static final String dateFormat = "yyyy-MM-dd";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(dateFormat);

    Set<DateTimeFormatter> dateTimeFormatters = new HashSet<>(Arrays.asList(
        DateTimeFormatter.ISO_DATE_TIME,
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    ));

    @Override
    public LocalDateTime convert(String source) {

        if (StringUtils.isEmpty(source.trim())) {
            return null;
        }

        // patterns 를 돌면서 날짜타입으로 정상 전환되면 반환한다.
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
