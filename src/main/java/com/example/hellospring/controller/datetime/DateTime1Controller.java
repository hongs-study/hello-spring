package com.example.hellospring.controller.datetime;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 테스트1 - 기본
 * 문자형의 날짜 요청데이터를 LocalDateTime으로 받는 방법을 알아본다.
 * 다양한 요청데이터 받는 방법으로 알아본다.
 */
@RestController
@RequestMapping("/datetime/v1")
public class DateTime1Controller {

    @GetMapping("/{datetime}")
    public String requestparam(@PathVariable("datetime") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime datetime) {
        System.out.println(datetime);
        return datetime.toString();
    }

    @GetMapping("/query")
    public String queryparam(@RequestParam("datetime") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime datetime) {
        System.out.println(datetime);
        return datetime.toString();
    }

    @GetMapping("/model")
    public String model(@ModelAttribute DateTimeRequest data) {
        LocalDateTime datetime = data.getDatetime();
        System.out.println(datetime);
        return datetime.toString();
    }

    @PostMapping("/body")
    public String requestBody(@RequestBody DateTimeRequest data) {
        LocalDateTime datetime = data.getDatetime();
        System.out.println(datetime);
        return datetime.toString();
    }
}