package com.example.hellospring.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestTest {

    @GetMapping("/test/request/array")
    public void test(@RequestParam("array") List<String> array) {
        array.forEach(System.out::println);
    }

}
