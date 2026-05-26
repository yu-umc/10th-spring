package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;

    // 지역별 수행 가능 미션(홈 화면 쿼리)
    @GetMapping("/region/{regionID}")
    public ApiResponse<MissionResDTO.MissionListDTO> getRegionMissions(
            @PathVariable(name = "regionID") Long regionID,
            @RequestParam(defaultValue = "READY") MissionStatus status,
            @RequestParam(defaultValue = "0") Integer page
    ) {
        // MissionService의 메서드를 호출
        MissionResDTO.MissionListDTO response = missionService.getMissionsByRegionRaw(regionID, status, page);

        return ApiResponse.onSuccess(response);
    }
}
