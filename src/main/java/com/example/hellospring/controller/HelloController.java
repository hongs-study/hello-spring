package com.example.hellospring.controller;

import com.example.hellospring.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    private final CustomService customService;

    @Autowired
    public HelloController(CustomService customService) {
        this.customService = customService;
    }

    @GetMapping("/hello/custom")
    public String customTest(Model model) {
        customService.testString();
        return "hello";
    }

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello world!!");
        return "hello";
    }

    @GetMapping("/hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @ResponseBody
    @GetMapping("/hello-string")
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @ResponseBody
    @GetMapping("/hello-api")
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    private class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
