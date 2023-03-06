package com.example.hellospring.repository;

import com.example.hellospring.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

//@ExtendWith(SpringExtension.class)

//@DataJpaTest

// @SpringBootTest + EntityManager 쓸 때 => @Transactional 필수
@Transactional
@SpringBootTest
class SpringDataJpaMemberRepositoryTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void test() {
        //given
        Member member = new Member();
        member.setId(1L);
        member.setName("아이샤");
        Member saveMem = memberRepository.save(member);
        Long id = saveMem.getId();
        em.flush();
        em.clear();

        //when
        Member findMem = memberRepository.findById(id).get();

        //then
        assertThat(findMem.getId()).isEqualTo(saveMem.getId());
        assertThat(findMem.getName()).isEqualTo(saveMem.getName());
    }

}