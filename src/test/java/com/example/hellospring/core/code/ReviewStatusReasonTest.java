package com.example.hellospring.core.code;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class ReviewStatusReasonTest {

    @Test
    void test() {
        List<ReviewStatusReason> list = ReviewStatusReason.getListBy(ReviewStatus.DELETED, ReviewStatus.BLINDED);

        list.forEach(e -> System.out.println(e));
    }

}