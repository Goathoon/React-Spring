package com.example.SpringAndReact.controller;

import com.example.SpringAndReact.Domain.Member;
import com.example.SpringAndReact.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public String createForm(@RequestBody MemberForm form){
        Member member = new Member();
        System.out.println(form.getUserId());
//        member.setUserId(form.getUserId());
//        member.setPassword(form.getPassword());
//        memberService.join(member);

        return "login";
    }
};
