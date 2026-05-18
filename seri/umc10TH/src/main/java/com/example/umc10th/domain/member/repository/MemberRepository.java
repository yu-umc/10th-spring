package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELET m FROM Member m WHERE m.name = :name AND m.deletedAt IS NULL")
    Optional<Member> findActiveMember(String name);

    void deleteByName(String name);
}
