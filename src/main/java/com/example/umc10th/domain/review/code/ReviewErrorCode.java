package com.example.umc10th.domain.review.code;

import com.example.umc10th.global.apiPayload.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404", "리뷰를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
