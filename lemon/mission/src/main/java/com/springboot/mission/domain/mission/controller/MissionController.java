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

    /**
     * 홈 화면: 지역별 미션 페이징 조회
     */
    @PostMapping("/home/local")
    public ResponseEntity<MissionResponseDTO.MissionPageResponse> getHomeMissionPage(
            @RequestBody MissionRequestDTO.SearchMission request,
            @RequestParam(name = "page", defaultValue = "0") int page) {

        // 서비스 메서드명을 Page에 맞게 호출
        MissionResponseDTO.MissionPageResponse response =
                missionService.getMissionPageByRegion(request.keyword(), page);

        return ResponseEntity.ok(response);
    }

    /**
     * 마이페이지: 내 미션 페이징 조회
     */
    @GetMapping("/user/{userId}/missions")
    public ResponseEntity<MissionResponseDTO.MyMissionPageResponse> getMyMissionPage(
            @PathVariable(name = "userId") Long userId,
            @RequestHeader("Authorization") String token,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "status", required = false) Boolean status) {

        MissionResponseDTO.MyMissionPageResponse response =
                missionService.getMyMissionPage(userId, token, page, status);

        return ResponseEntity.ok(response);
    }

    /**
     * 미션 상태 업데이트 및 결과 페이지 반환
     */
    @PutMapping("/user/{userId}/mission/{missionId}")
    public ResponseEntity<MissionResponseDTO.MyMissionPageResponse> updateMissionStatusAndGetPage(
            @PathVariable(name = "userId") Long userId,
            @PathVariable(name = "missionId") Long missionId,
            @RequestBody MissionRequestDTO.ClearMission request,
            @RequestHeader("Authorization") String token) {

        MissionResponseDTO.MyMissionPageResponse response =
                missionService.updateMissionStatusAndGetPage(userId, missionId, request, token);

        return ResponseEntity.ok(response);
    }
}
