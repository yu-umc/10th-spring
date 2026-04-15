package com.springboot.mission.domain.mission.dto;

import lombok.Builder;

public class MissionRequestDTO {
    @Builder
    public record SearchMission(
            String keyword
    ){}

    @Builder
    public record ClearMission(
            Boolean state
    ){}
}
