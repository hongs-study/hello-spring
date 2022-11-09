package com.example.hellospring.core.code;

import static com.example.hellospring.core.code.ReviewStatus.BLINDED;
import static com.example.hellospring.core.code.ReviewStatus.DELETED;

import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 리뷰 상태 사유 코드
 */
@AllArgsConstructor
@Getter
public enum ReviewStatusReason {

    RESOLVED_BLIND_REASON_01(BLINDED, "사실과 다른 내용", true),
    RESOLVED_BLIND_REASON_02(BLINDED, "정확한 이용일 확인이 어렵거나 이용 후 7일 이상 경과한 경우", true),
    RESOLVED_BLIND_REASON_03(BLINDED, "내용과 맞지 않는 평점을 남긴 경우", true),
    RESOLVED_BLIND_REASON_04(BLINDED, "평점 조작 어뷰징 리뷰", true),
    RESOLVED_BLIND_REASON_05(BLINDED, "기타", true),

    RESOLVED_DELETED_REASON_01(DELETED, "욕설, 비속어, 음란성 내용을 포함한 게시물", true),
    RESOLVED_DELETED_REASON_02(DELETED, "도배, 스팸, 광고성 게시물", true),
    RESOLVED_DELETED_REASON_03(DELETED, "방문한 숙소와 전혀 관계없는 내용의 게시물", true),
    RESOLVED_DELETED_REASON_04(DELETED, "리모델링, 사업주 변경 (포인트 차감 없음)", true),
    RESOLVED_DELETED_REASON_05(DELETED, "타업체를 언급한 게시물", true),
    RESOLVED_DELETED_REASON_06(DELETED, "개인정보 포함 및 유출 위험이 있는 게시물", true),
    RESOLVED_DELETED_REASON_07(DELETED, "예약취소", true),
    RESOLVED_DELETED_REASON_08(DELETED, "숙소를 이용하지 않고 작성된 게시물 (포인트 차감 없음)", true)
    ;

    private final ReviewStatus reviewStatus; // 리뷰 상태
    private final String title;
    private final boolean isPointMinus; // 포인트 차감 여부

    public List<ReviewStatusReason> getReviewStatus(ReviewStatus ...reviewStatus) {
        List<ReviewStatus> reviewStatusList = Arrays.stream(reviewStatus).toList();
        return Arrays.stream(values())
            .filter(o -> reviewStatusList.contains(o.getReviewStatus()))
            .toList();
    }

}
