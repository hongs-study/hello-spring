package com.example.hellospring.controller.datetime;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.format.annotation.DateTimeFormat;

//@JsonDeserialize(converter = StringToUtcDateTimeConverter.class) // 작동 안 됨.
@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
@Inherited
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UtcLocalDateTime {
}
