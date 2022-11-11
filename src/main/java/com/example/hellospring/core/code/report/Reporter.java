package com.example.hellospring.core.code.report;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 리뷰 신고자 코드
 */
@AllArgsConstructor
@Getter
public enum Reporter {
    ALL("전체"),
    USER("사용자"),
    HOST("점주")
    ;

    private final String title;
}