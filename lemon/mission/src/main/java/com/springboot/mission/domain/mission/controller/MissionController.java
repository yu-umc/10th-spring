package com.springboot.mission.domain.mission.controller;

import com.springboot.mission.domain.mission.dto.MissionRequestDTO;
import com.springboot.mission.domain.mission.dto.MissionResponseDTO;
import com.springboot.mission.domain.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/home/local")
    public ResponseEntity<MissionResponseDTO.MissionListResponse> getHomeMissions(
            @RequestBody MissionRequestDTO.SearchMission request) {

        // page 값이 없으므로 기본값 0을 강제로 주입
        MissionResponseDTO.MissionListResponse response =
                missionService.getMissionList(request.keyword(), 0);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}/missions")
    public ResponseEntity<MissionResponseDTO.MyMissionListResponse> getUserMissions(
            @PathVariable(name = "userId") Long userId,
            @RequestHeader("Authorization") String token,
            @RequestParam(name = "page", defaultValue = "0") int page) {

        MissionResponseDTO.MyMissionListResponse response = missionService.getMyMissionList(userId, token, page);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/user/{userId}/mission/{missionId}")
    public ResponseEntity<MissionResponseDTO.MyMissionListResponse> updateMissionState(
            @PathVariable(name = "userId") Long userId,
            @PathVariable(name = "missionId") Long missionId,
            @RequestBody MissionRequestDTO.ClearMission request,
            @RequestHeader("Authorization") String token) {

        MissionResponseDTO.MyMissionListResponse response =
                missionService.clearMissionAndGetList(userId, missionId, request, token);

        return ResponseEntity.ok(response);
    }
}
