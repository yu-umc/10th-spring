package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;

import java.util.List;
import java.util.stream.Collectors;


public class MissionConverter {

    public static MissionResDTO.RequestBody toRequestBody(
            String stringTest,
            Long longTest
    ) {
        return null;
    }

    public static MissionResDTO.MissionDetailDTO toMissionDetailDTO(Mission mission) {
        return MissionResDTO.MissionDetailDTO.builder()
                .missionId(mission.getId())
                .point(mission.getPoint())
                .storeName(mission.getStore().getStore_name())
                .missionSpec(mission.getMissionSpec())
                .deadline(mission.getDeadline())
                .build();
    }

    public static MissionResDTO.MissionListDTO toMissionListDTO(List<Mission> missionList) {
        List<MissionResDTO.MissionDetailDTO> missionDetailDTOList = missionList.stream()
                .map(MissionConverter::toMissionDetailDTO)
                .collect(Collectors.toList());

        return MissionResDTO.MissionListDTO.builder()
                .missionList(missionDetailDTOList)
                .listSize(missionDetailDTOList.size())
                .build();
    }
}
