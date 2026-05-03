package com.example.umc10th.domain.user.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserSuccessCode implements BaseSuccessCode {
    USER_OK(HttpStatus.OK,
            "USER200_1",
            "성공적으로 처리됐습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
