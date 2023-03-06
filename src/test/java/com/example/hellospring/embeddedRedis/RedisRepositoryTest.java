package com.example.hellospring.embeddedRedis;

import com.example.hellospring.RedisConfig;
import com.example.hellospring.repository.RedisRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

// 필요한 빈만 등록하여 테스트 : classes에 지정한 클래스만 빈등록하여 테스트를 실행한다. 즉, 전체 빈등록보다 빠르다
@SpringBootTest(classes = {TestRedisConfiguration.class, RedisRepository.class, RedisConfig.class})
class RedisRepositoryTest {

    @Autowired
    private RedisRepository redisRepository;

    @Test
    public void saveAndFind() {
        String value = "hello world!";
        String key = redisRepository.save(value);
        System.out.println("key = " + key + ", value = " + value);

        String findValue = redisRepository.findByKey(key);
        System.out.println("findValue = " + findValue);

        assertThat(findValue).isEqualTo(value);
    }

}