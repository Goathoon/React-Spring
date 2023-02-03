package com.example.SpringAndReact.Repository;

import com.example.SpringAndReact.Domain.Member;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member saveMember(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findByUserId(String id) {
        return store.values().stream().filter(member->member.getUsername().equals(id)).findAny();
    }

    @Override
    public Optional<Member> findMember(String id, String password) {
        return store.values().stream()
                .filter(member -> (member.getUsername().equals(id) && member.getPassword().equals(password)))
                .findAny();
    }
}
