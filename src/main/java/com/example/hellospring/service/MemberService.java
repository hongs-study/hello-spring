package com.example.hellospring.service;

import com.example.hellospring.domain.Member;
import com.example.hellospring.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member.getName());
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(String name) {
        memberRepository.findByName(name)
            .ifPresent(m -> {
                throw new IllegalStateException("이미 같은 이름의 회원이 존재합니다.");
            });
    }

    /**
     * 전체 멤버 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 1명의 멤버 조회
     */
    public Optional<Member> findOne(Long id) {
        return memberRepository.findById(id);
    }
}
