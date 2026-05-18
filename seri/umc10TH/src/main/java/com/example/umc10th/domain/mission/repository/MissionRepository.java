package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.dto.MissionResDTO;

public interface MissionRepository {
    public List<MissionResDTO.GetMission> getMissions(
            Long storeId
    ){

        List<Mission> missionList = missionRepository.findAllByStore_id(stroeId);

        return missionList.stream()
                .map(MissionConverter::toGetMission)
                .toList();
    }
}
