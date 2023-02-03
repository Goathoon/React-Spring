package com.example.SpringAndReact.Service;

import com.example.SpringAndReact.Domain.Member;

import java.util.Optional;


public interface MemberService {
    Long join(Member member);
    void validateDuplicateMember(Member member);
    Optional<Member> checkIdPassword(Member member);
}
