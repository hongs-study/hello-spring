package com.example.hellospring.controller.datetime;

import java.time.LocalDateTime;
import javax.validation.constraints.Min;
import org.springframework.format.annotation.DateTimeFormat;
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
 * Resolver로 어디까지 캐치 되는지 확인해본다.
 */
@Validated
@RestController
@RequestMapping("/datetime/v2")
public class DateTime2Controller {

    @GetMapping("/{datetime}")
    public String requestparam(@UtcLocalDateTime @PathVariable("datetime") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime datetime) {
        System.out.println(datetime);
        return datetime.toString();
    }

    @GetMapping("/query")
    public String queryparam(@UtcLocalDateTime @RequestParam("datetime") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime datetime) {
        System.out.println(datetime);
        return datetime.toString();
    }

    @GetMapping("/query2")
    public String queryparam2(@UtcLocalDateTime String datetime) {
        System.out.println(datetime);
        return datetime.toString();
    }

    @GetMapping("/query3")
    public String queryparam3(@RequestParam("datetime") @Min(value = 5, message = "5 이상 입력하세요") Integer datetime) {
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
