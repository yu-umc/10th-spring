package com.example.umc10th.domain.user.dto;

import com.example.umc10th.domain.mission.enums.MissionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


public class UserResDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserMissionDetailDTO {
        Long missionId;
        Long point;
        String missionSpec;
        String storeName;
        MissionStatus status;
        LocalDate deadline;
    }

    @Builder
    public record Pagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){}
}
