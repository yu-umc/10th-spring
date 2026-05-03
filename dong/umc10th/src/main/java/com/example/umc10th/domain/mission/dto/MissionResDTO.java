package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

public class MissionResDTO {

    @Builder
    public record RequestBody(
            String stringTest,
            Long longTest
    ){}
}
