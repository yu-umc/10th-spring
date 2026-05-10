package com.springboot.mission.domain.mission.repository;

import com.springboot.mission.domain.mission.entity.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // 특정 사용자와 특정 미션의 연관 데이터를 조회
    Optional<MemberMission> findByUserIdAndMissionId(Long userId, Long missionId);
}
