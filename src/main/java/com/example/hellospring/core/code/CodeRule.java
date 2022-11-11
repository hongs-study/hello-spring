package com.example.hellospring.core.code;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import org.springframework.util.Assert;

public interface CodeRule {

    BiPredicate<Enum<?>, String> defaultEqualPredicateString = (thisType, string) -> {
        Assert.notNull(thisType, "thisType can not be null!");
        return thisType.name().equals(string);
    };

    String getTitle();
    boolean eq(String string);
    default boolean eq(Enum<?> type) {
        return this.equals(type);
    }


    /**
     * 문자열로 Enum 객체 찾기 (기본값)
     * 문자열과 일치하는 Enum 객체가 있다면 반환하고, 없다면 defaultType 을 반환한다.
     *
     * @param enumType Enum 클래스
     * @param enumString Enum 에서 찾을 객체의 문자열
     * @param defaultType enumString 와 일치하는 Enum 객체가 없다면 반환 할 기본값 (Enum)
     * @return enumString 와 일치하는 Enum Type 객체 or defaultType
     */
    static <T extends Enum> T of(Class<T> enumType, String enumString, T defaultType) {
        T returnType = defaultType;
        try {
            returnType = (T) Enum.valueOf(enumType, enumString);
        } catch (IllegalArgumentException ignored) {}
        return returnType;
    }

    /**
     * 문자열로 Enum 객체 찾기 (Optional)
     * 문자열과 일치하는 Enum 객체가 있다면 반환하고, 없다면 Optional.empty() 을 반환한다.
     *
     * @param enumType Enum 클래스
     * @param enumString Enum 에서 찾을 객체의 문자열
     * @return enumString 와 일치하는 Enum Type Optional 객체
     */
    static <T extends Enum> Optional<T> of(Class<T> enumType, String enumString) {
        T findEnum = null;
        try {
            findEnum = (T) Enum.valueOf(enumType, enumString);
        } catch (IllegalArgumentException ignored) {}
        return Optional.ofNullable(findEnum);
    }

    /**
     * code, codeName 형태의 리스트를 반환한다.
     * @param enumType 대상 Enum 클래스
     */
    static List<CodeVo> codeAndCodeNameList(Class<? extends CodeRule> enumType) {
        List<CodeVo> collect = Arrays.stream(enumType.getEnumConstants())
            .map(e -> (CodeRule) e)
            .map(CodeVo::new)
            .collect(Collectors.toList());
        return collect;
    }
}