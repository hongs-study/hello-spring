package com.example.hellospring.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.hellospring.domain.Member;
import com.example.hellospring.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @DisplayName("[회원가입] 성공 테스트.")
    @Test
    void join() {
        Member member = Member.builder().name("홍길동").build();

        Long joinId = memberService.join(member);
        Member findMember = memberService.findOne(joinId).orElseThrow(() -> new RuntimeException("실패"));

        Assertions.assertThat(member.getId()).isEqualTo(findMember.getId());
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @DisplayName("[회원가입] 실패 테스트. 중복된 사용자가 있다.")
    @Test
    void joinDupleFail() {
        Member member1 = Member.builder().name("홍길동").build();
        memberService.join(member1);

        Member member2 = Member.builder().name("홍길동").build();
        IllegalStateException result = assertThrows(IllegalStateException.class,
            () -> {
                memberService.join(member2);
            }, "테스트실패. 중복된 이름으로 회원가입 할 수 없어야 한다.");

        assertThat(result.getMessage()).isEqualTo("이미 같은 이름의 회원이 존재합니다.");
    }
}