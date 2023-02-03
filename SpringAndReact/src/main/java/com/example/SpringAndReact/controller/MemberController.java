package com.example.SpringAndReact.controller;

import com.example.SpringAndReact.Domain.Member;
import com.example.SpringAndReact.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("api/auth")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    @ResponseBody
    public Member createForm(@RequestBody MemberForm form) {
        Member member = new Member();
        member.setUsername(form.getUsername());
        member.setPassword(form.getPassword());
        memberService.join(member);
        return member;
    }
    @PostMapping("/login")
    @ResponseBody // Login이 확인되었는지 Front에서 알게하기 위해 Member 객체를 payload로 넘겨주기 위함이다.
    public <Optional> Member login(@RequestBody MemberForm form){
        Member member = new Member();
        member.setUsername(form.getUsername());
        member.setPassword(form.getPassword());
        java.util.Optional<Member> result = memberService.checkIdPassword(member);
        if (!result.isPresent()){
    
        }
    }
};
