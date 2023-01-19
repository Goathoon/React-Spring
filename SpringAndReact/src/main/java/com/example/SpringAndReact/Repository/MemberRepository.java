package com.example.SpringAndReact.Repository;

import com.example.SpringAndReact.Domain.Member;

import java.util.Optional;

public interface MemberRepository {
    Member saveMember(Member member);
    Optional<Member> findByUserId(String id);
}
