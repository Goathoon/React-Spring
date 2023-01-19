package com.example.SpringAndReact.controller;

import com.example.SpringAndReact.Domain.Member;
import com.example.SpringAndReact.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth/register")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/api/auth/register")
    public String createForm(MemberForm form){
        Member member = new Member();
        member.setUserId(form.getUserId());
        member.setPassword(form.getPassword());
        memberService.join(member);

        return "redirect:/";
    }
};
