package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

public class MissionResDTO {

    @Builder
    public record GetMission(
            Long missionId,
            Integer point,
            String conditional
    ){}

    @Builder
    public record Pagination<T>(
            List<T> data,
            Boolean hasNext,
            Integer pageNumber,
            Integer pageSize
    ){}
}
