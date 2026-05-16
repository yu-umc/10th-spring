package com.springboot.mission.domain.mission.repository;

import com.springboot.mission.domain.mission.entity.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // 특정 사용자와 특정 미션의 연관 데이터를 조회
    Optional<MemberMission> findByUserIdAndMissionId(Long userId, Long missionId);

    // 특정 유저의 전체 미션 내역 페이징 조회
    Page<MemberMission> findAllByUserId(Long userId, Pageable pageable);

    // 특정 유저의 미션 중 아직 완료되지 않은(진행 중인) 내역만 페이징 조회
    Page<MemberMission> findAllByUserIdAndIsCompletedFalse(Long userId, Pageable pageable);
}
