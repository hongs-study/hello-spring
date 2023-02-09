package com.example.hellospring.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.hellospring.domain.Member;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    void clearData() {
        repository.deleteAll();
    }

    @DisplayName("회원 도메인과 리파지토리의 작동을 확인한다.")
    @Test
    void testMember() {
        Member member = Member.builder().name("홍길동").build();

        repository.save(member);

        Member result = repository.findById(member.getId())
            .orElseThrow(() -> new RuntimeException("에러"));

        assertThat(member).isEqualTo(result);
    }

    @DisplayName("회원 이름으로조회")
    @Test
    void findByName() {
        //given
        Member member1 = Member.builder().name("홍길동").build();
        repository.save(member1);

        Member member2 = Member.builder().name("홍길동").build();
        repository.save(member2);

        Member result = repository.findByName("홍길동2")
            .orElseThrow(() -> new RuntimeException("실패"));
        assertThat(result).isEqualTo(member2);
    }

    @DisplayName("전체조회")
    @Test
    void findAll() {
        //given
        Member member1 = Member.builder().name("홍길동").build();
        repository.save(member1);

        Member member2 = Member.builder().name("홍길동").build();
        repository.save(member2);

        List<Member> all = repository.findAll();

        assertThat(all.size()).isEqualTo(2);
    }
}