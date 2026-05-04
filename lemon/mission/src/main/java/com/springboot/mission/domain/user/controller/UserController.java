package com.springboot.mission.domain.user.controller;

import com.springboot.mission.domain.user.dto.UserRequestDTO;
import com.springboot.mission.domain.user.dto.UserResponseDTO;
import com.springboot.mission.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<UserResponseDTO.JoinResponse> joinUser(
            @RequestBody UserRequestDTO.JoinUser request) {

        userService.join(request);

        UserResponseDTO.JoinResponse response = UserResponseDTO.JoinResponse.builder()
                .message("회원가입에 성공했습니다.")
                .build();

        return ResponseEntity.ok(response);
    }
}
