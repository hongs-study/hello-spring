package com.example.hellospring.converter;

import com.example.hellospring.controller.datetime.UtcLocalDateTime;
import com.example.hellospring.utils.DateTimeUtil;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

/**
 * 전역에 적용됨
 */
//@Component
@Deprecated
public class StringToDateTimeConverter implements GenericConverter {

    Set<DateTimeFormatter> patterns = new HashSet<>(Arrays.asList(
        DateTimeFormatter.ISO_DATE,
        DateTimeFormatter.ISO_DATE_TIME,
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    ));

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(String.class, LocalDateTime.class));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        String strDateTime = (String) source;

        if (StringUtils.isEmpty(strDateTime.trim())) {
            throw new RuntimeException("에러");
        }

        // patterns 를 돌면서 날짜타입으로 정상 전환되면 반환한다.
        for (DateTimeFormatter pattern : patterns) {
            try {
                LocalDateTime parsedDateTime = LocalDateTime.parse(strDateTime, pattern);
                System.out.println(" * 입력(KST) = " + parsedDateTime);

                // check 어노테이션 붙어있는지 체크
                if (targetType.hasAnnotation(UtcLocalDateTime.class)) {
                    ZonedDateTime zonedDateTime = DateTimeUtil.utcFromKst(parsedDateTime);
                    System.out.println(" * zonedDateTime = " + zonedDateTime);
                    parsedDateTime = zonedDateTime.toLocalDateTime();
                    System.out.println(" * 변환(UTC) = " + parsedDateTime);
                }
                return parsedDateTime;
            } catch (NullPointerException | DateTimeParseException ignored) {}
        }

        throw new RuntimeException("에러");
    }
}
