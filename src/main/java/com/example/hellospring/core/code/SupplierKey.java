package com.example.hellospring.core.code;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SupplierKey {
    AGODA("AGODA"), GC("GC"), UNKNOWN("UNKNOWN");
    private static final Map<String, SupplierKey> supplierKeys =
        Collections.unmodifiableMap(Stream.of(values())
            .collect(Collectors.toMap(SupplierKey::toString, Function.identity())));
    private final String supplierKey;

    SupplierKey(String supplierKey) {
        this.supplierKey = supplierKey;
    }

    public static SupplierKey of(String serviceKey) {
        return Optional.ofNullable(supplierKeys.get(serviceKey)).orElse(UNKNOWN);
    }

    @Override
    public String toString() {
        return supplierKey;
    }

}
