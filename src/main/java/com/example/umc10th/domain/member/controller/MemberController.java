package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    // 2주차 미션이었던 마이페이지 조회 API 예시
    @PostMapping("/me")
    public ApiResponse<MemberResponseDTO.MyPageResultDTO> getMyPage(
            @RequestBody MemberRequestDTO.MyPageRequestDTO request
    ){
        //지금은 Service가 없으므로 컨트롤러 구조만 잡음
        return ApiResponse.onSuccess(null);
    }

}
