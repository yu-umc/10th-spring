package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    public MissionResDTO.MissionListDTO getMissionsByRegionRaw(Long regionID, MissionStatus status, Integer page) {
        int limit = 10;
        int offset = page * limit; // 페이지 번호에 따라 시작 지점 계산

        List<Mission> missionList = missionRepository.findAllByRegionAndStatusRaw(
                regionID,
                status.toString(),
                limit,
                offset
        );
        return MissionConverter.toMissionListDTO(missionList);
    }
}
