package com.example.SpringAndReact.Repository;

import com.example.SpringAndReact.Domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member saveMember(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findByUserId(String id) {
        List<Member> result = em.createQuery("select m from Member m where m.username = :name", Member.class)
                .setParameter("name", id)
                .getResultList();
        return result.stream().findAny();
    }
}
