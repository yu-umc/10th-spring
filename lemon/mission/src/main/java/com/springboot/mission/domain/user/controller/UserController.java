package com.springboot.mission.domain.user.controller;

import com.springboot.mission.domain.user.dto.UserRequestDTO;
import com.springboot.mission.domain.user.dto.UserResponseDTO;
import com.springboot.mission.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 회원 가입 API
     */
    @PostMapping("/join")
    public ResponseEntity<UserResponseDTO.JoinResponse> joinUser(
            @RequestBody @Valid UserRequestDTO.JoinUser request) {

        // 서비스에서 생성된 결과를 그대로 반환하여 응답의 일관성을 유지합니다.
        UserResponseDTO.JoinResponse response = userService.join(request);
        return ResponseEntity.ok(response);
    }

    /**
     * 마이페이지 정보 조회 API (이메일, 닉네임 등)
     */
    @GetMapping("/{userId}/mypage")
    public ResponseEntity<UserResponseDTO.MyPageInfo> getMyPage(
            @PathVariable(name = "userId") Long userId,
            @RequestHeader(value = "Authorization", required = false) String token) {

        // 서비스의 getMyPageInfo 호출
        UserResponseDTO.MyPageInfo response = userService.getMyPageInfo(userId);

        return ResponseEntity.ok(response);
    }
}
