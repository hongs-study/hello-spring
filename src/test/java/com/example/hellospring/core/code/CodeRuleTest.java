package com.example.hellospring.core.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CodeRuleTest {

    static Map<String, ReviewStatus> review = new HashMap<>();

    private ReviewStatus active = ReviewStatus.ACTIVE;
    private ReviewStatus deleted = ReviewStatus.DELETED;

    @BeforeAll
    static void beforeAll() {
        review.put("status", null);
    }

    @DisplayName("1. equals() 사용시 주의점")
    @Test
    void test1() {
        ReviewStatus reviewStatus = review.get("status");
        //System.out.println(reviewStatus.equals(ReviewStatus.ACTIVE));
        System.out.println(ReviewStatus.ACTIVE.equals(null));

    }

    @DisplayName("2. 문자열만 보고 같은 값 여부를 판단하는")
    @Test
    void test2() {

        String text = "ACTIVE";

        System.out.println(
            ReviewStatus.ACTIVE.name().equals(text)
        );
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    @DisplayName("객체찾기1: Enum 기본 함수 사용")
    @Test
    void find() {
        ReviewStatus find = ReviewStatus.valueOf("ACTIVE");
        System.out.println("find = " + find);
        Assertions.assertTrue(ReviewStatus.ACTIVE.equals(find));
    }

    @DisplayName("객체찾기2: Optional 객체로 반환")
    @Test
    void find2() {
        Optional<ReviewStatus> 객체1 = CodeRule.of(ReviewStatus.class, "ACTIVE");
        Assertions.assertEquals(ReviewStatus.ACTIVE, 객체1.get());

        Optional<ReviewStatus> 빈객체 = CodeRule.of(ReviewStatus.class, "없음");
        Assertions.assertTrue(빈객체.isEmpty());
    }

    @DisplayName("객체찾기3: 기본값 지정하기(유틸 이용)")
    @Test
    void findOrDefault() {
        ReviewStatus requested = CodeRule.of(ReviewStatus.class, "ACTIVE", ReviewStatus.ACTIVE);
        System.out.println("findString1 >> " + requested);
        Assertions.assertTrue("ACTIVE".equals(requested.name()));

        ReviewStatus defaultType = CodeRule.of(ReviewStatus.class, "DELETED", ReviewStatus.DELETED);
        System.out.println("findString2(defaultType) >> " + defaultType);
        Assertions.assertTrue(ReviewStatus.DELETED.equals(defaultType));
    }

    @DisplayName("비교테스트1: eq(Type)")
    @Test
    void eqTest() {
        Assertions.assertTrue(ReviewStatus.ACTIVE.eq(active));
        Assertions.assertFalse(ReviewStatus.ACTIVE.eq(deleted));
    }

    @DisplayName("비교테스트2: eq(String)")
    @Test
    void eqStringTest() {
        Assertions.assertTrue(ReviewStatus.ACTIVE.eq("ACTIVE"));
        Assertions.assertFalse(ReviewStatus.ACTIVE.equals("TEST"));
        Assertions.assertFalse(ReviewStatus.ACTIVE.equals(""));
    }

    @DisplayName("리스트 조회 테스트")
    @Test
    void getListTest() {
        List<CodeVo> codes = CodeRule.codeAndCodeNameList(ReviewStatus.class);
        codes.forEach(System.out::println);

        CodeVo typeNameVo = codes.get(0);
        System.out.println("code = " + typeNameVo.getCode().getClass());
        System.out.println("codeName = " + typeNameVo.getCodeName().getClass());
    }

/*    @DisplayName("객체찾기3: 기본값 지정하기(직접구현)")
    @Test
    void findOrDefaultImpl() {

        String findString1 = "SITUATION";
        CancelReasonType situation = CancelReasonType.of(findString1);
        System.out.println("findString1=" + findString1 + ", result=" + situation);
        Assertions.assertTrue(CancelReasonType.SITUATION.equals(situation));

        String findString2 = "NOTHING";
        CancelReasonType defaultType = CancelReasonType.of(" ");
        System.out.println("findString2=" + findString2 + ", result(defaultType)=" + defaultType);
        Assertions.assertTrue(CancelReasonType.ETC.equals(defaultType));
    }*/
}