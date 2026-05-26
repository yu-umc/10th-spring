package com.example.umc10th.domain.user.converter;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.user.dto.UserResDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserConverter {

    public static UserResDTO.UserMissionDetailDTO toUserMissionDetailDTO(UserMission userMission){
        return UserResDTO.UserMissionDetailDTO.builder()
                .missionId(userMission.getMission().getId())
                .point(userMission.getMission().getPoint())
                .missionSpec(userMission.getMission().getMissionSpec())
                .storeName(userMission.getMission().getStore().getStore_name())
                .status(userMission.getStatus())
                .deadline(userMission.getMission().getDeadline())
                .build();
    }

    public static <T> UserResDTO.Pagination<T> toPagination(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){
        return UserResDTO.Pagination.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }
}
