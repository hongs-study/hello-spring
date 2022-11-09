package com.example.hellospring.core.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReviewReportStatus {
    NO_REPORT("미신고 리뷰"),
    HAVE_OPEN_REPORT("신고접수 리뷰"),
    DONE_REPORT("승인완료 리뷰");

    private final String title;
}
