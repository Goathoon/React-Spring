package com.example.SpringAndReact.Repository;

import com.example.SpringAndReact.Domain.Member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member saveMember(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }
}
