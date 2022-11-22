package com.example.hellospring;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    private static final ZoneId UTC = ZoneId.of("UTC");
    private static final ZoneId KST = ZoneId.of("Asia/Seoul");

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T' HH:mm:ss z";

    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // 방법2 - API 응답 전역으로 적용
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonMapper() {
        return builder -> {
            builder.simpleDateFormat(DATE_FORMAT);
            builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(DATE_FORMAT)));
            //builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)));
            builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializerCustom());
            builder.serializerByType(ZonedDateTime.class, new ZonedDateTimeSerializerCustom());
        };
    }

    public static class LocalDateTimeSerializerCustom extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            String formattedDateTime = ZonedDateTime.of(value, UTC).withZoneSameInstant(KST).format(DATETIME_FORMATTER);
            System.out.println("LocalDateTimeSerializerCustom >> Before(UTC) :: " + value + " >> After(KST) :: " + formattedDateTime);
            gen.writeString(formattedDateTime);
        }
    }

    private static class ZonedDateTimeSerializerCustom extends JsonSerializer<ZonedDateTime> {
        @Override
        public void serialize(ZonedDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            String formattedDateTime = value.withZoneSameInstant(KST).format(DATETIME_FORMATTER);
            System.out.println("ZonedDateTimeSerializerCustom >> Before(UTC) :: " + value + " >> After(KST) :: " + formattedDateTime);
            gen.writeString(formattedDateTime);
        }
    }

    // todo 방법3 - 커스텀모듈 (각필드 or DTO 클래스에 적용)
}
