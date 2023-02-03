package com.example.SpringAndReact.Repository;

import com.example.SpringAndReact.Domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

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

    @Override
    public Optional<Member> findMember(String id, String password) {
        findByUserId(id).ifPresent(mem ->{
            try{
                throw new IllegalAccessException("세상에 그런 아이디는 등록된 적이 없어요!");
            }catch(IllegalAccessException e){
                throw new RuntimeException(e);
            }
        });
        TypedQuery<Member> query = em.createQuery("select m from Member m where m.username = :name and m.password = :password", Member.class);
        query.setParameter("name",id);
        query.setParameter("password",password);
        List<Member> resultList = query.getResultList();
        if (!resultList.isEmpty()){
            try{
                throw new IllegalAccessException("password를 다시 확인해 주세요!");
            } catch(IllegalAccessException e){
                throw new RuntimeException(e);
            }
        }
        return resultList.stream().findAny();
    };
}
