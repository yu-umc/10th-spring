package com.example.umc10th.domain.user.repository;

import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    Page<UserMission> findAllByUserIdAndStatus(Long userID, MissionStatus status, PageRequest pageRequest);

}
