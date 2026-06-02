package com.example.umc10th.domain.user.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthSuccessCode implements BaseSuccessCode {
    USER_OK(HttpStatus.OK,
            "USER200_1",
            "성공적으로 처리됐습니다."),
    USER_SIGNUP_OK(HttpStatus.OK,
            "USER200_2",
            "성공적으로 회원가입됐습니다."),
    USER_LOGIN_OK(HttpStatus.OK,
            "User200_3",
            "성공적으로 로그인됐습니다.");
    private final HttpStatus status;
    private final String code;
    private final String message;
}
