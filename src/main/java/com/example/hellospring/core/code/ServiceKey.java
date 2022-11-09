package com.example.hellospring.core.code;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ServiceKey {
    STAY_OVERSEA("STAY_OVERSEA"),
    STAY_DOMESTIC("STAY_DOMESTIC"),
    RENT_PLACE("RENT_PLACE"),
    ACTIVITY("ACTIVITY"),
    UNKNOWN("UNKNOWN");

    private static final Map<String, ServiceKey> serviceKeys =
        Collections.unmodifiableMap(Stream.of(values())
            .collect(Collectors.toMap(ServiceKey::toString, Function.identity())));
    private final String serviceKey;

    ServiceKey(String serviceKey) {
        this.serviceKey = serviceKey;
    }

    public static Optional<ServiceKey> of(String serviceKey) {
        return Optional.ofNullable(serviceKeys.get(serviceKey));
    }

    @Override
    public String toString() {
        return serviceKey;
    }

}
