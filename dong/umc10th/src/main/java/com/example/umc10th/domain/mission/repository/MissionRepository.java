package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
    @Query(value = "SELECT m.* FROM mission m " + // s.*를 빼고 m.*만 가져옵니다!
            "JOIN store s ON m.store_id = s.store_id " +
            "JOIN region r ON s.region_id = r.region_id " +
            "WHERE r.region_id = :regionId AND m.status = :status " +
            "LIMIT :limit OFFSET :offset",
            nativeQuery = true)
    List<Mission> findAllByRegionAndStatusRaw(
            @Param("regionId") Long regionId,
            @Param("status") String status,
            @Param("limit") int limit,
            @Param("offset") int offset
    );
}
