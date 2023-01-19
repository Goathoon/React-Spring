package com.example.SpringAndReact.Service;

import com.example.SpringAndReact.Domain.Member;

public interface MemberService {
    Long join(Member member);
    void validateDuplicateMember(Member member);
}
