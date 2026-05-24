package com.springboot.mission.domain.user.code;

import com.springboot.mission.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    // 이 부분이 서비스에서 사용할 "중복 회원" 에러 코드입니다.
    MEMBER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "MEMBER4001", "이미 가입된 회원입니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER4002", "사용자를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}