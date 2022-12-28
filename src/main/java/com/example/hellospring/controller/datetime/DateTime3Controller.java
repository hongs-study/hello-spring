package com.example.hellospring.controller.datetime;

import java.time.LocalDateTime;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Vlidation이 의도대로 작동하는지 체크한다
 */
@Validated
@RestController
@RequestMapping("/datetime/v3")
public class DateTime3Controller {

    @GetMapping("/{datetime}")
    public String requestparam(@PathVariable("datetime") @UtcLocalDateTime LocalDateTime datetime) {
        System.out.println(datetime);
        return datetime.toString();
    }

    @GetMapping("/query")
    public String queryparam(@UtcLocalDateTime @RequestParam("datetime") LocalDateTime datetime) {
        System.out.println(datetime);
        return datetime.toString();
    }

    @GetMapping("/model")
    public String model(@Validated @ModelAttribute DateTimeRequest data) {
        LocalDateTime datetime = data.getDatetime();
        System.out.println(datetime);
        return datetime.toString();
    }

    @PostMapping("/body")
    public ResponseData requestBody(@RequestBody DateTimeRequest data) {
        LocalDateTime datetime = data.getDatetime();
        System.out.println("UTC:::: " + datetime);
        ResponseData responseData = new ResponseData();
        responseData.setResponseDatetime(datetime);
        return responseData;
    }
}
