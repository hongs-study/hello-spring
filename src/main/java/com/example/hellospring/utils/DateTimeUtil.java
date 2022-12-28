package com.example.hellospring.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateTimeUtil {

    private final static ZoneId UTC = ZoneId.of("UTC");
    private final static ZoneId KST = ZoneId.of("Asia/Seoul");

    public static ZonedDateTime kstFrom(LocalDateTime dateTime) {
        return ZonedDateTime.of(dateTime, UTC).withZoneSameInstant(KST);
    }

    public static ZonedDateTime kstFrom(long epochSeconds) {
        return Instant.ofEpochSecond(epochSeconds).atZone(KST);
    }

    public static ZonedDateTime kstNow() {
        return ZonedDateTime.now(KST);
    }

    public static ZonedDateTime utcFrom(LocalDateTime dateTime) {
        return ZonedDateTime.of(dateTime, UTC);
    }

    public static ZonedDateTime utcFrom(long epochSeconds) {
        return Instant.ofEpochSecond(epochSeconds).atZone(UTC);
    }

    public static ZonedDateTime utcNow() {
        return ZonedDateTime.now(UTC);
    }

    public static ZonedDateTime utcFromKst(LocalDateTime dateTime) {
        return ZonedDateTime.of(dateTime, KST).withZoneSameInstant(UTC);
    }

    public static ZonedDateTime kstFromUtc(LocalDateTime utcDateTime) {
        return ZonedDateTime.of(utcDateTime, UTC).withZoneSameInstant(KST);
    }
}
