package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.user.converter.UserConverter;
import com.example.umc10th.domain.user.dto.AuthReqDTO;
import com.example.umc10th.domain.user.dto.AuthResDTO;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.exception.code.AuthSuccessCode;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ApiResponse<AuthResDTO.joinResult> join(
            @RequestBody @Valid AuthReqDTO.Join request
    ) {
        User user = userService.joinUser(request);
        BaseSuccessCode code = AuthSuccessCode.USER_SIGNUP_OK;
        return ApiResponse.onSuccess(code,UserConverter.tojoinResult(user));
    }

    @PostMapping("/login")
    public ApiResponse<AuthResDTO.LoginResult> login(
            @RequestBody AuthReqDTO.Login request
    ) {
        AuthResDTO.LoginResult result = userService.loginUser(request);
        BaseSuccessCode code = AuthSuccessCode.USER_LOGIN_OK;
        return ApiResponse.onSuccess(code,result);
    }

}
