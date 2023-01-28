package com.example.SpringAndReact.Repository;

import com.example.SpringAndReact.Domain.Member;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    public Member saveMember(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Optional<Member> findByUserId(String userId) {
        return store.values().stream().filter(member -> member.getUsername().equals(userId)).findAny();
    }

    // 230127 siwon
    public Optional<Member> checkUserIDPassword(String userId, String password) {
        return store.values().stream()
                .filter(member -> (member.getUsername().equals(userId) && member.getPassword().equals(password)))
                .findAny();
    }
}
