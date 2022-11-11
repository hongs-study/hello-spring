package com.example.hellospring.core.code.report;

import static com.example.hellospring.core.code.report.ReportType.NORMAL_REPORT;
import static com.example.hellospring.core.code.report.ReportType.RIGHT_REPORT;
import static com.example.hellospring.core.code.report.Reporter.ALL;
import static com.example.hellospring.core.code.report.Reporter.HOST;

import com.example.hellospring.core.code.ReviewStatus;
import com.example.hellospring.core.code.ReviewStatusReason;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 신고 사유 코드
 */
@AllArgsConstructor
@Getter
public enum ReportReason {

    RESOLVED_DELETED_REASON_01(ALL, NORMAL_REPORT, "욕설, 비속어, 음란성 내용을 포함한 게시물"),
    RESOLVED_DELETED_REASON_02(ALL, NORMAL_REPORT, "도배, 스팸, 광고성 게시물"),
    RESOLVED_DELETED_REASON_03(ALL, NORMAL_REPORT, "방문한 숙소와 전혀 관계없는 내용의 게시물"),
    RESOLVED_DELETED_REASON_05(ALL, NORMAL_REPORT, "타업체를 언급한 게시물"),
    RESOLVED_DELETED_REASON_06(ALL, NORMAL_REPORT, "개인정보 포함 및 유출 위험이 있는 게시물"),
    RESOLVED_DELETED_REASON_04(HOST, NORMAL_REPORT, "리모델링, 사업주 변경"),
    RESOLVED_DELETED_REASON_07(HOST, NORMAL_REPORT, "예약취소"),
    RESOLVED_DELETED_REASON_08(HOST, RIGHT_REPORT, "권리침해")
    ;

    private final Reporter reporter;        // 신고자 구분 (유저, 업주)
    private final ReportType reportType;    // 신고 유형 (일반, 권리침해)
    private final String title;

    /**
     * 신고자별 목록
     */
    public static List<ReportReason> getListBy(Reporter... reporter) {
        List<Reporter> searchReporters = Arrays.stream(reporter).toList();
        return Arrays.stream(values())
            .filter(o -> searchReporters.contains(o.getReporter()))
            .toList();
    }

    /**
     * 신고유형별 목록
     */
    public static List<ReportReason> getListBy(ReportType... reportType) {
        List<ReportType> searchReportType = Arrays.stream(reportType).toList();
        return Arrays.stream(values())
            .filter(o -> searchReportType.contains(o.getReportType()))
            .toList();
    }

}
