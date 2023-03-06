package com.example.hellospring.embeddedRedis;

import com.example.hellospring.repository.RedisRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

//@DataJpaTest
@SpringBootTest(classes = TestRedisConfiguration.class)
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