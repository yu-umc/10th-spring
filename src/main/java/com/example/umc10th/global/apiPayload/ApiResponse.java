package com.example.umc10th.global.apiPayload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;
    private final String code;
    private final String message;
    private final T result;

    // ✅ 성공할 때
    public static <T> ApiResponse<T> of(BaseSuccessCode successCode, T result) {
        return new ApiResponse<>(true,
                successCode.getCode(),
                successCode.getMessage(),
                result);
    }

    public static <T> ApiResponse<T> onSuccess(T result) {
        return new ApiResponse<>(true, "COMMON200", "성공입니다.", result);
    }

    // ❌ 실패할 때
    public static <T> ApiResponse<T> onFailure(BaseErrorCode errorCode, T data) {
        return new ApiResponse<>(false,
                errorCode.getCode(),
                errorCode.getMessage(),
                data);
    }
}
