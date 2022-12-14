package com.example.hellospring;

import com.example.hellospring.repository.SpringDataJpaMemberRepository;
import com.example.hellospring.service.CustomService;
import com.example.hellospring.service.MemberService;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;
    private final EntityManager em;
    private final SpringDataJpaMemberRepository repository;

    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em,
        SpringDataJpaMemberRepository repository) {
        this.dataSource = dataSource;
        this.em = em;
        this.repository = repository;
    }

    /*@Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
        //return new JpaMemberRepository(em);
    }*/

    @Bean
    public MemberService memberService() {
        //return new MemberService(memberRepository());
        return new MemberService(repository);
    }

    @Bean
    public CustomService customService() {
        return new CustomService();
    }
}
