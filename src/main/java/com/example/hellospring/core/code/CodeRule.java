package com.example.hellospring.core.code;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

/**
 * Enum 클래스 규칙 & Enum 클래스 유틸
 * <pre>
 *     추상 메서드, default 메서드 는 각 Enum 클래스에서 Override 재정의 할 수 있습니다
 * </pre>
 */
public interface CodeRule {

    BiPredicate<Enum<?>, String> defaultEqualPredicateString = (thisType, string) -> thisType.name().equals(string);

    String getCodeName();

    boolean eq(String string);

    default boolean eq(Enum<?> type) {
        return this.equals(type);
    }



    /**
     * 문자열로 Enum 객체 찾기 (Optional)
     * <pre>
     *     문자열과 일치하는 Enum 객체가 있다면 반환하고, 없다면 Optional.empty() 을 반환한다.
     *     사용 예 : CodeRule.of(ReviewStatus.class, "ACTIVE");
     * </pre>
     *
     * @return Optional
     */
    static <T extends Enum> Optional<T> of(Class<T> enumType, String enumString) {
        T findEnum = null;
        try {
            findEnum = (T) Enum.valueOf(enumType, enumString);
        } catch (IllegalArgumentException ignored) {}
        return Optional.ofNullable(findEnum);
    }

    /**
     * 문자열로 Enum 객체 찾기 (기본값)
     * <pre>
     *     문자열과 일치하는 Enum 객체가 있다면 반환하고, 없다면 defaultType 을 반환한다.
     *     사용 예 : CodeRule.of(ReviewStatus.class, "ACTIVE", ReviewStatus.ACTIVE);
     * </pre>
     * @param defaultType 기본값 Enum 객체
     * @return Enum 객체
     */
    static <T extends Enum> T of(Class<T> enumType, String enumString, T defaultType) {
        T returnType = defaultType;
        try {
            returnType = (T) Enum.valueOf(enumType, enumString);
        } catch (IllegalArgumentException ignored) {}
        return returnType;
    }

    /**
     * code, codeName 리스트 반환 (전체)
     * <pre>
     *     사용 예 : CodeRule.codeAndCodeNameList(ReviewStatus.class);
     *     결과 예 : [
     *          CodeVo(code=ACTIVE, codeName=일반)
     *          CodeVo(code=BLINDED, codeName=블라인드)
     *          CodeVo(code=DELETED, codeName=삭제)
     *     ]
     * </pre>
     */
    static List<CodeVo> codeAndCodeNameList(Class<? extends CodeRule> enumType) {
        return Arrays.stream(enumType.getEnumConstants())
            .map(CodeVo::new)
            .collect(Collectors.toList());
    }

    /**
     * code, codeName 리스트 반환 (입력받은 값만 변환)
     * <pre>
     *     사용 예 :
     *          {@code
     *              List<ReviewStatusReason> reviewStatusList = ReviewStatusReason.getListBy(ReviewStatus.BLINDED);
     *              List<CodeVo> codes = CodeRule.codeAndCodeNameList(reviewStatusList);
     *          }
     *     결과 예 : [
     *          CodeVo(code=RESOLVED_BLIND_REASON_01, codeName=사실과 다른 내용)
     *          CodeVo(code=RESOLVED_BLIND_REASON_02, codeName=정확한 이용일 확인이 어렵거나 이용 후 7일 이상 경과한 경우)
     *          CodeVo(code=RESOLVED_BLIND_REASON_03, codeName=내용과 맞지 않는 평점을 남긴 경우)
     *          CodeVo(code=RESOLVED_BLIND_REASON_04, codeName=평점 조작 어뷰징 리뷰)
     *          CodeVo(code=RESOLVED_BLIND_REASON_05, codeName=기타)
     *     ]
     * </pre>
     */
    static List<CodeVo> codeAndCodeNameList(Collection<? extends CodeRule> targetList) {
        return targetList.stream()
            .map(CodeVo::new)
            .collect(Collectors.toList());
    }
}