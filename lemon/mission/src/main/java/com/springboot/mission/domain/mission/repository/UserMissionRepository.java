package com.springboot.mission.domain.mission.repository;

import com.springboot.mission.domain.mission.entity.UserMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.ScopedValue;
import java.util.Optional;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    Page<UserMission> findAllByUserIdAndIsCompletedFalse(Long userId, PageRequest pageRequest);

    Page<UserMission> findAllByUserId(Long userId, PageRequest pageRequest);

    Optional<UserMission> findByUserIdAndMissionId(Long userId, Long missionId);
}
