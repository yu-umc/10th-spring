package com.springboot.mission.domain.mission.repository;

import com.springboot.mission.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    /**
     * 지역명(Region의 category 또는 name)을 기준으로 미션 페이지를 조회합니다.
     * Mission 엔티티 내부의 region 필드를 참조하여 검색합니다.
     */
    Page<Mission> findAllByRegionName(String regionName, Pageable pageable);
}
