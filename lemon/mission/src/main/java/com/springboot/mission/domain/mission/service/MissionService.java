package com.springboot.mission.domain.mission.service;

import com.springboot.mission.domain.mission.dto.MissionRequestDTO;
import com.springboot.mission.domain.mission.dto.MissionResponseDTO;
import com.springboot.mission.domain.mission.entity.MemberMission;
import com.springboot.mission.domain.mission.entity.Mission;
import com.springboot.mission.domain.mission.repository.MemberMissionRepository;
import com.springboot.mission.domain.mission.repository.MissionRepository;
import com.springboot.mission.domain.user.repository.UserRepository;
import com.springboot.mission.global.apiPayload.code.GeneralErrorCode;
import com.springboot.mission.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MissionRepository missionRepository;
    private final UserRepository userRepository;
    private final MemberMissionRepository memberMissionRepository;

    /**
     * 지역명으로 미션 페이지 조회
     */
    public MissionResponseDTO.MissionPageResponse getMissionPageByRegion(String keyword, int page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Mission> missionPage = missionRepository.findAllByRegionName(keyword, pageRequest);

        return MissionResponseDTO.MissionPageResponse.from(missionPage);
    }

    /**
     * 사용자의 미션 페이지 조회
     */
    public MissionResponseDTO.MyMissionPageResponse getMyMissionPage(Long userId, String token, int page, boolean status) {
        // 1. 유저 존재 검증
        userRepository.findById(userId)
                .orElseThrow(() -> new ProjectException(GeneralErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<MemberMission> myMissionPage;

        // 2. status 값에 따른 리포지토리 분기 처리
        if (!status) {
            // 진행 중인 미션만 조회 (isCompleted가 false인 데이터)
            myMissionPage = memberMissionRepository.findAllByUserIdAndIsCompletedFalse(userId, pageRequest);
        } else {
            // 전체 미션 조회
            myMissionPage = memberMissionRepository.findAllByUserId(userId, pageRequest);
        }

        // 3. 변환된 페이징 DTO 반환
        return MissionResponseDTO.MyMissionPageResponse.from(myMissionPage);
    }

    /**
     * 미션 상태 업데이트 후 최신 마이 미션 페이지 반환
     */
    @Transactional
    public MissionResponseDTO.MyMissionPageResponse updateMissionStatusAndGetPage(
            Long userId, Long missionId, MissionRequestDTO.ClearMission request, String token) {

        // 1. 해당 유저가 수행 중인 특정 미션 기록을 찾습니다.
        MemberMission memberMission = memberMissionRepository.findByUserIdAndMissionId(userId, missionId)
                .orElseThrow(() -> new ProjectException(GeneralErrorCode.NOT_FOUND));

        // 2. 실제 상태 업데이트 (Dirty Checking 발생)
        if (request.state()) {
            memberMission.complete();
        }

        // 3. 업데이트 결과가 반영된 페이지를 다시 조회하여 반환
        return getMyMissionPage(userId, token, 0, true);
    }
}