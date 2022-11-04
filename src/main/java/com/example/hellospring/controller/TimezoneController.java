package com.example.hellospring.controller;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimezoneController {

    private final TimezoneTestRepository timezoneTestRepository;

    @GetMapping("/application")
    public void applicatonTime() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("애플리케이션 LocalDateTime = " + now);
    }

    @GetMapping("/database")
    public List<TimezoneTest> saveDbAndGetTime() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("애플리케이션 LocalDateTime = " + now);
        timezoneTestRepository.save(new TimezoneTest(now));
        List<TimezoneTest> all = timezoneTestRepository.findAll();
        return all;
    }


    @GetMapping("/between")
    public List<TimezoneTest> saveDbAndGetTimeBetween() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("애플리케이션 LocalDateTime = " + now);
        timezoneTestRepository.save(new TimezoneTest(now));
        List<TimezoneTest> all = timezoneTestRepository.findAllByCreateAtBetween(
            LocalDateTime.of(2022,11,4,14,0),
            LocalDateTime.of(2022,11,4,15,0)
        );
        return all;
    }
}
