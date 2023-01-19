package com.example.SpringAndReact.Service;

import com.example.SpringAndReact.Domain.Member;
import com.example.SpringAndReact.Repository.MemberRepository;

public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.saveMember(member);
    }
}
