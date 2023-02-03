package com.example.SpringAndReact.Service;

import com.example.SpringAndReact.Domain.Member;
import com.example.SpringAndReact.Repository.MemberRepository;
import jakarta.transaction.Transactional;

import java.util.Optional;

@Transactional
public class MemberServiceImpl implements MemberService {
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
        memberRepository.findByUserId(member.getUsername()).ifPresent(mem -> {
            try {
                throw new IllegalAccessException("이미 존재하는 회원입니다.");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public Optional<Member> checkIdPassword(Member member) {
        Optional<Member> resultMember = memberRepository.findMember(member.getUsername(), member.getPassword());
        if (!resultMember.isPresent()){
            try{
                throw new IllegalAccessException("아이디, 비밀번호를 다시 확인해주세요.");
            } catch(IllegalAccessException e){
                throw new RuntimeException(e);
            }
        }
        return resultMember;
    }
}

