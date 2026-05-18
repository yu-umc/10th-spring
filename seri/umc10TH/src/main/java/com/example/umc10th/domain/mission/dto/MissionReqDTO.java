package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.NotNull;

public class MissionReqDTO {

    public record CreateMisson(
            @NotNull(message = "마감기한은 필수입니다.")
            LocalDate deadline,
            @NotNull(message = "미션 성공 포인트 필수입니다.")
            Integer point,
            @NotNull(message = "조건은 빈칸일 수 없습니다.")
            String conditional
    ){}
}
