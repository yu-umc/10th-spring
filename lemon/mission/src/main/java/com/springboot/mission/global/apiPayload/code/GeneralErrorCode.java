package com.springboot.mission.global.apiPayload.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

@Getter
@RequiredArgsConstructor
public enum GeneralErrorCode implements BaseErrorCode{
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,
            "COMMON500", "서버 내부 오류가 발생했습니다."),
    BAQ_REQUEST(HttpStatus.BAD_REQUEST,
            "COMMON400", "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,
            "COMMON401", "인증되지 않았습니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN,
            "COMMON403", "접근이 금지되었습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND,
            "COMMON404", "해당 리소스를 찾을 수 없습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;;
}
