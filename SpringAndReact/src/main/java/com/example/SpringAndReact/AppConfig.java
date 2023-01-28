package com.example.SpringAndReact;

import com.example.SpringAndReact.Repository.MemberRepository;
import com.example.SpringAndReact.Service.MemberService;
import com.example.SpringAndReact.Service.MemberServiceImpl;
import com.example.SpringAndReact.controller.MemberController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(new MemberRepository());
    }

}
