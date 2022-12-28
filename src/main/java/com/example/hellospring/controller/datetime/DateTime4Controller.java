package com.example.hellospring.controller.datetime;

import java.time.LocalDateTime;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 날짜계산 for between 조건을 위해 endDate를 구해본다.
 */
@Validated
@RestController
@RequestMapping("/datetime/v4")
public class DateTime4Controller {

    @GetMapping("/between")
    public ResponseData between(
        @UtcLocalDateTime @RequestParam("startDate") LocalDateTime startDate,
        @UtcLocalDateTime @RequestParam("endDate") LocalDateTime endDate
    ) {
        System.out.println("startDate = " + startDate);
        System.out.println("endDate = " + endDate);

        // 쿼리에서 아래와같이 endDate를 세팅해야한다
        LocalDateTime endDateTime = endDate.plusDays(1).minusSeconds(1);
        System.out.println("endDateTime = " + endDateTime);

        ResponseData responseData = new ResponseData();
        responseData.setResponseDatetime(endDateTime);
        return responseData;
    }
}
