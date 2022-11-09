package com.example.hellospring.core.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReviewStatus implements CodeRule {
    ACTIVE("일반"),
    BLINDED("블라인드"),
    DELETED("삭제");

    private final String title;

    @Override
    public boolean eq(String string) {
        return defaultEqualPredicateString.test(this, string);
    }

}
