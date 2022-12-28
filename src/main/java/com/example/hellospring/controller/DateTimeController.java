package com.example.hellospring.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DateTimeController {

    @GetMapping("/date/get/1")
    public String getTest1(
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dateTime
    ) {
        log.info("date :: {}, dateTime :: {} ", date, dateTime);
        return "1. get @RequestParam @DateTimeFormat OK";
    }

    @GetMapping("/date/get/2")
    public String getTest2(@ModelAttribute DateTimeParam2 param) {
        log.info("date :: {}, dateTime :: {} ", param.getDate(), param.getDateTime());
        return "2. get @ModelAttribute @DateTimeFormat OK";
    }

    @GetMapping("/date/get/3")
    public String getTest3(@ModelAttribute DateTimeParam3 param) {
        log.info("date :: {}, dateTime :: {} ", param.getDate(), param.getDateTime());
        return "3. get @ModelAttribute @JsonFormat OK?";
    }

    @PostMapping("/date/post/4")
    public String getTest4(@RequestBody DateTimeParam4 param) {
        log.info("date :: {}, dateTime :: {} ", param.getDate(), param.getDateTime());
        return "4. post @RequestBody @JsonFormat OK";
    }

    @PostMapping("/date/post/5")
    public String getTest5(@RequestBody DateTimeParam5 param) {
        log.info("date :: {}, dateTime :: {} ", param.getDate(), param.getDateTime());
        return "5. post @RequestBody @DateTimeFormat OK";
    }
}
