package com.example.hellospring.config;

import com.example.hellospring.utils.DateTimeUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
//@Configuration
public class DateTimeDateBindGlobalConfig {

    private static final String dateFormat = "yyyy-MM-dd";
    private static final String dateTimeFormat = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(dateFormat);
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(dateTimeFormat);

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {
            builder.simpleDateFormat(dateFormat);
            builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer());
            builder.deserializers(new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
        };
    }

    public static final class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            String kstString = DateTimeUtil.kstFromUtc(value).toLocalDateTime().format(DATETIME_FORMATTER);
            log.info(" UTC={}  ===>  KST={}", value, kstString);
            gen.writeString(kstString);
        }
    }

    public static final class LocalDateTimeDeserializer extends com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer {

        public LocalDateTimeDeserializer(DateTimeFormatter dateTimeFormatter) {
            super(dateTimeFormatter);
        }

        @Override
        protected LocalDateTime _fromString(JsonParser p, DeserializationContext ctxt, String strDateTime) throws IOException {
            try {
                LocalDateTime kstDateTime = LocalDateTime.parse(strDateTime, DATETIME_FORMATTER);
                ZonedDateTime utcZonedDateTime = DateTimeUtil.utcFromKst(kstDateTime);
                LocalDateTime utcDateTime = utcZonedDateTime.toLocalDateTime();
                log.info(" KST={}  ===>  UTC={}", kstDateTime, utcDateTime);
                return super._fromString(p, ctxt, utcDateTime.format(DATETIME_FORMATTER));
            } catch (NullPointerException | DateTimeParseException ignored) {}

            try {
                LocalDate parsedDate = LocalDate.parse(strDateTime, DATE_FORMATTER);
                LocalDateTime kstDateTime = LocalDateTime.of(parsedDate, LocalTime.MIN);
                ZonedDateTime utcZonedDateTime = DateTimeUtil.utcFromKst(kstDateTime);
                LocalDateTime utcDateTime = utcZonedDateTime.toLocalDateTime();
                log.info(" KST={}  ===>  UTC={}", kstDateTime, utcDateTime);
                return super._fromString(p, ctxt, utcDateTime.format(DATETIME_FORMATTER));
            } catch (NullPointerException | DateTimeParseException ignored) {}

            return super._fromString(p, ctxt, "");
        }
    }
}
