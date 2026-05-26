package com.example.umc10th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {

    @Builder
    public record RequestBody(
            String stringTest,
            Long longTest
    ){}

    // 전체 목록을 담는 그릇 (컨트롤러에서 리턴하는 타입)
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionListDTO {
        List<MissionDetailDTO> missionList; // 개별 미션들을 리스트로 담음
        Integer listSize;                   // 현재 목록에 담긴 미션 개수
    }

    // 개별 미션의 상세 정보
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionDetailDTO {
        Long missionId;
        Long point;
        String missionSpec;
        String storeName;
        LocalDate deadline;
    }
}
