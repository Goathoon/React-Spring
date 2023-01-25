package com.example.SpringAndReact;

import com.example.SpringAndReact.Repository.MemoryMemberRepository;
import com.example.SpringAndReact.Service.MemberService;
import com.example.SpringAndReact.Service.MemberServiceImpl;
import com.example.SpringAndReact.controller.MemberController;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    public AppConfig(EntityManager em){
        this.em = em;
    }
    @Bean
    public MemberService memberService (){
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

}
