package com.example.hellospring.core.code.report;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReportReasonTest {

    @DisplayName("신고자별 목록")
    @Test
    void getListByReporter() {
        // 1개 파라미터
        List<ReportReason> listBy = ReportReason.getListBy(Reporter.HOST);
        Assertions.assertThat(listBy.size()).isEqualTo(3);
        assertTrue(listBy.contains(ReportReason.RESOLVED_DELETED_REASON_04));
        assertTrue(listBy.contains(ReportReason.RESOLVED_DELETED_REASON_07));
        assertTrue(listBy.contains(ReportReason.RESOLVED_DELETED_REASON_08));

        // n개 파라미터
        List<ReportReason> listBy2 = ReportReason.getListBy(Reporter.HOST, Reporter.ALL);
        Assertions.assertThat(listBy2.size()).isEqualTo(8);
        assertTrue(listBy2.contains(ReportReason.RESOLVED_DELETED_REASON_04));
        assertTrue(listBy2.contains(ReportReason.RESOLVED_DELETED_REASON_07));
        assertTrue(listBy2.contains(ReportReason.RESOLVED_DELETED_REASON_08));

        assertTrue(listBy2.contains(ReportReason.RESOLVED_DELETED_REASON_01));
        assertTrue(listBy2.contains(ReportReason.RESOLVED_DELETED_REASON_02));
        assertTrue(listBy2.contains(ReportReason.RESOLVED_DELETED_REASON_03));
        assertTrue(listBy2.contains(ReportReason.RESOLVED_DELETED_REASON_05));
        assertTrue(listBy2.contains(ReportReason.RESOLVED_DELETED_REASON_06));
    }

    @DisplayName("신고유형별 목록")
    @Test
    void getListByReportType() {
        // 1개 파라미터
        List<ReportReason> listBy1 = ReportReason.getListBy(ReportType.NORMAL_REPORT);
        Assertions.assertThat(listBy1.size()).isEqualTo(7);
        assertTrue(listBy1.contains(ReportReason.RESOLVED_DELETED_REASON_01));
        assertTrue(listBy1.contains(ReportReason.RESOLVED_DELETED_REASON_02));
        assertTrue(listBy1.contains(ReportReason.RESOLVED_DELETED_REASON_03));
        assertTrue(listBy1.contains(ReportReason.RESOLVED_DELETED_REASON_05));
        assertTrue(listBy1.contains(ReportReason.RESOLVED_DELETED_REASON_06));
        assertTrue(listBy1.contains(ReportReason.RESOLVED_DELETED_REASON_04));
        assertTrue(listBy1.contains(ReportReason.RESOLVED_DELETED_REASON_07));

        // n개 파라미터
        List<ReportReason> listBy2 = ReportReason.getListBy(ReportType.NORMAL_REPORT, ReportType.RIGHT_REPORT);
        Assertions.assertThat(listBy2.size()).isEqualTo(8);
        assertTrue(listBy2.contains(ReportReason.RESOLVED_DELETED_REASON_01));
        assertTrue(listBy2.contains(ReportReason.RESOLVED_DELETED_REASON_02));
        assertTrue(listBy2.contains(ReportReason.RESOLVED_DELETED_REASON_03));
        assertTrue(listBy2.contains(ReportReason.RESOLVED_DELETED_REASON_05));
        assertTrue(listBy2.contains(ReportReason.RESOLVED_DELETED_REASON_06));
        assertTrue(listBy2.contains(ReportReason.RESOLVED_DELETED_REASON_04));
        assertTrue(listBy2.contains(ReportReason.RESOLVED_DELETED_REASON_07));
        assertTrue(listBy2.contains(ReportReason.RESOLVED_DELETED_REASON_08));
    }
}