package com.example.hellospring.config;

import com.example.hellospring.converter.KstStringToUtcDateTimeConverter;
import com.example.hellospring.converter.KstStringToUtcDateTimeGlobalConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new KstStringToUtcDateTimeConverter()); // 부분적용 시
        //registry.addConverter(new KstStringToUtcDateTimeGlobalConverter()); // 글로벌 적용 시
    }
}
