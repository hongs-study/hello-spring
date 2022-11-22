package com.example.hellospring.core.code;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ReviewStatusReasonTest {

    @Test
    void test() {
        List<ReviewStatusReason> list = ReviewStatusReason.getListBy(ReviewStatus.DELETED, ReviewStatus.BLINDED);

        list.forEach(e -> System.out.println(e));
    }


    @Test
    void test2() {

        Set<String> set = new HashSet<>();

        System.out.println(set.contains("1"));

    }
}