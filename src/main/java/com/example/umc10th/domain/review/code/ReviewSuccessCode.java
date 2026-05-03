package com.example.umc10th.domain.review.code;


import com.example.umc10th.global.apiPayload.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    REVIEW_CREATED(HttpStatus.CREATED, "REVIEW201", "리뷰 저장 성공입니다."),
    REVIEW_LIST_OK(HttpStatus.OK,      "REVIEW200", "리뷰 조회 성공입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}