package com.example.hellospring.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

//@DataJpaTest
@SpringBootTest
class RedisRepositoryTest {

    @Autowired
    private RedisRepository redisRepository;

    @Test
    public void saveAndFind() {
        String value = "hello world!";
        String key = redisRepository.save(value);

        String byKey = redisRepository.findByKey(key);

        assertThat(byKey).isEqualTo(value);
    }

}