package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

public interface MemberRepository extends JpaRepository<Member, Long> {
    void deleteByName(String name);
}
