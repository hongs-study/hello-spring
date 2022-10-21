package com.example.hellospring.controller;

import com.example.hellospring.domain.Member;
import com.example.hellospring.service.MemberService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String joinMember(MemberForm memberForm) {
        Member member = new Member();
        member.setName(memberForm.getName());
        Long memberId = memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String findMembers(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
