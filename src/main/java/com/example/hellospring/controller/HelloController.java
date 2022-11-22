package com.example.hellospring.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    @GetMapping("/hello-api")
    public Hello helloApi(@RequestParam("date") LocalDateTime date) {
        System.out.println("PathVariable >> 요청날짜 : " + date);
        Hello hello = new Hello();
        return hello;
    }

    @GetMapping("/hello-api/{date}")
    public Hello helloApi(@PathVariable("date") String date) {
        System.out.println("RequestParam >> 요청날짜 : " + LocalDateTime.parse(date, dateTimeFormatter));
        Hello hello = new Hello();
        return hello;
    }

    @PostMapping("/hello-api")
    public Hello helloApi(@RequestBody Hello hello1) {
        System.out.println("PathVariable >> 요청날짜 : " + hello1.getDate());
        Hello hello = new Hello();
        return hello;
    }
}
