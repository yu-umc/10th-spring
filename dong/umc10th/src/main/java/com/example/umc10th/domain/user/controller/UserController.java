package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    // 내 미션을 모아서 보는 쿼리(진행중/완료)
    @GetMapping("/missions")
    public ApiResponse<UserResDTO.Pagination<UserResDTO.UserMissionDetailDTO>> getUserMissions(
            @RequestBody @Valid UserReqDTO.UserIdReqDTO request,
            @RequestParam(defaultValue = "CHALLENGING") MissionStatus status,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false) String sort
    ) {
            UserResDTO.Pagination<UserResDTO.UserMissionDetailDTO> response = userService.getMissionsByUserID(request.userId(), status, pageSize, pageNumber, sort);
        BaseSuccessCode code = MissionSuccessCode.MISSION_OK;
        return ApiResponse.onSuccess(code, response);
    }

//    // 마이 페이지 조회
//    @GetMapping("/{userId}/mypage")
//    public ApiResponse<UserResDTO.MyPageInfo> getMyPage(
//            @PathVariable(name = "userId") Long userId
//    ) {
//        UserResDTO.MyPageInfo response = userService.getMyPageInfo();
//    }
}
