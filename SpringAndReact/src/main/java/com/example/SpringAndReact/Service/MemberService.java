package com.example.SpringAndReact.Service;

import com.example.SpringAndReact.Domain.Member;
import jakarta.transaction.Transactional;


public interface MemberService {
    Long join(Member member);
    void validateDuplicateMember(Member member);
}
