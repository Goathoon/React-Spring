package com.example.SpringAndReact;

import com.example.SpringAndReact.Repository.MemoryMemberRepository;
import com.example.SpringAndReact.Service.MemberService;
import com.example.SpringAndReact.Service.MemberServiceImpl;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    public MemberService memberService (){
        return new MemberServiceImpl(new MemoryMemberRepository());
    }
}
