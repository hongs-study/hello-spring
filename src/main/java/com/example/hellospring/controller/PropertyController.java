package com.example.hellospring.controller;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller
public class PropertyController {

    @Value("${test.main-id}")
    private String mainId;

    @Value("${test.main-name}")
    private String mainName;

    @Value("${test.core-id}")
    private String coreId;

    @Value("${test.core-name}")
    private String coreName;

    @Value("${test.security-id}")
    private String securityId;

    @Value("${test.security-name}")
    private String securityName;

    @PostConstruct
    public void test() {
        System.out.println("mainId = " + mainId);
        System.out.println("mainName = " + mainName);
        System.out.println("coreId = " + coreId);
        System.out.println("coreName = " + coreName);
        System.out.println("securityId = " + securityId);
        System.out.println("securityName = " + securityName);
    }
}
