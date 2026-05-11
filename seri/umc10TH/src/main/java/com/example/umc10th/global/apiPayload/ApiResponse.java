package com.example.umc10th.global.apiPayload;


import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "Code", "message", "result"})
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("result")
    private T result;

    public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T result){
        return new ApiResponse<>(isSuccess: false, code.getCode(), code.getMessage(), result)
    }
}
