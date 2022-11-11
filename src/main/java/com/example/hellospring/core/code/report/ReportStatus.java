package com.example.hellospring.core.code.report;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 신고 상태 코드
 */
@AllArgsConstructor
@Getter
public enum ReportStatus {
    OPEN("접수"),
    RESOLVED("승인"),
    CANCELED("반려");

    private final String title;
}
