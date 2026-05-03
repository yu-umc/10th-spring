package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;

public class MissionConverter {

    public static MissionResDTO.RequestBody toRequestBody(
            String stringTest,
            Long longTest
    ){
        return MissionResDTO.RequestBody.builder()
                .stringTest((stringTest)
                .longTest(longTest)
                .build();
    }
}
