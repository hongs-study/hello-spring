package com.example.hellospring;

import com.example.hellospring.repository.MemberRepository;
import com.example.hellospring.service.CustomService;
import com.example.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;
    private final EntityManager em;
    private final MemberRepository repository;

    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em, MemberRepository repository) {
        this.dataSource = dataSource;
        this.em = em;
        this.repository = repository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(repository);
    }

    @Bean
    public CustomService customService() {
        return new CustomService();
    }
}
