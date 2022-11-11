package com.example.hellospring.core.code.report;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 신고 유형 코드
 */
@AllArgsConstructor
@Getter
public enum ReportType {
    NORMAL_REPORT("일반 신고"),
    RIGHT_REPORT("권리 침해 신고")
    ;
    private final String title;
}