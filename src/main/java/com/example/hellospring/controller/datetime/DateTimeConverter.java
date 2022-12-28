package com.example.hellospring.controller.datetime;

import java.time.LocalDateTime;
import org.springframework.core.convert.converter.Converter;

public class DateTimeConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String source) {
        return null;
    }
}
