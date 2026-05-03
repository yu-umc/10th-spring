package com.example.umc10th.domain.mission.dto;

import lombok.Getter;

public class MissionReqDTO {

    public record RequestBody(
            String stringTest,
            Long longTest
    ){}
}
