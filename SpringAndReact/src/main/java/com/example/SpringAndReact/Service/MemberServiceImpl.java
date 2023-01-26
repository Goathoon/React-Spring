package com.example.SpringAndReact.Service;

import com.example.SpringAndReact.Domain.Member;
import com.example.SpringAndReact.Repository.MemberRepository;
import jakarta.transaction.Transactional;

@Transactional
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Override
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.saveMember(member);
        return member.getId(); //고유 id 반환
    }
    @Override
    public void validateDuplicateMember(Member member) {
        memberRepository.findByUserId(member.getUsername()).ifPresent(mem->{
            try{
                throw new IllegalAccessException("이미 존재하는 회원입니다.");
            }catch(IllegalAccessException e){
                throw new RuntimeException(e);
            }
        });
    }
}
