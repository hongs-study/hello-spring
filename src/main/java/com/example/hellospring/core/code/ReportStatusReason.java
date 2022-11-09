package com.example.hellospring.core.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 리뷰 상태 사유 코드, 신고 사유 코드
 */
@AllArgsConstructor
@Getter
public enum ReportStatusReason {

    RESOLVED_BLIND_REASON_01(ReportStatus.RESOLVED, ReviewStatus.BLINDED, "사실과 다른 내용"),
    RESOLVED_BLIND_REASON_02(ReportStatus.RESOLVED, ReviewStatus.BLINDED, "정확한 이용일 확인이 어렵거나 이용 후 7일 이상 경과한 경우"),
    RESOLVED_BLIND_REASON_03(ReportStatus.RESOLVED, ReviewStatus.BLINDED, "내용과 맞지 않는 평점을 남긴 경우"),
    RESOLVED_BLIND_REASON_04(ReportStatus.RESOLVED, ReviewStatus.BLINDED, "평점 조작 어뷰징 리뷰"),
    RESOLVED_BLIND_REASON_05(ReportStatus.RESOLVED, ReviewStatus.BLINDED, "기타"),

    RESOLVED_DELETED_REASON_01(ReportStatus.RESOLVED, ReviewStatus.DELETED, "욕설, 비속어, 음란성 내용을 포함한 게시물"),
    RESOLVED_DELETED_REASON_02(ReportStatus.RESOLVED, ReviewStatus.DELETED, "도배, 스팸, 광고성 게시물"),
    RESOLVED_DELETED_REASON_03(ReportStatus.RESOLVED, ReviewStatus.DELETED, "방문한 숙소와 전혀 관계없는 내용의 게시물"),
    RESOLVED_DELETED_REASON_04(ReportStatus.RESOLVED, ReviewStatus.DELETED, "리모델링, 사업주 변경 (포인트 차감 없음)"),
    RESOLVED_DELETED_REASON_05(ReportStatus.RESOLVED, ReviewStatus.DELETED, "타업체를 언급한 게시물"),
    RESOLVED_DELETED_REASON_06(ReportStatus.RESOLVED, ReviewStatus.DELETED, "개인정보 포함 및 유출 위험이 있는 게시물"),
    RESOLVED_DELETED_REASON_07(ReportStatus.RESOLVED, ReviewStatus.DELETED, "예약취소"),
    RESOLVED_DELETED_REASON_08(ReportStatus.RESOLVED, ReviewStatus.DELETED, "숙소를 이용하지 않고 작성된 게시물 (포인트 차감 없음)"),

    CANCEL_REASON_01(ReportStatus.CANCELED, null, "해당 리뷰는 선택하신 사유에 해당되지 않습니다."),
    CANCEL_REASON_02(ReportStatus.CANCELED, null, "이미 조치된 리뷰입니다."),
    CANCEL_REASON_03(ReportStatus.CANCELED, null, "기타")
    ;

    private final ReportStatus reportStatus;
    private final ReviewStatus reviewStatus;
    private final String description;
}
