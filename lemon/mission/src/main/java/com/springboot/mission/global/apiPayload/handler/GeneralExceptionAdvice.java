package com.springboot.mission.global.apiPayload.handler;

import com.springboot.mission.global.apiPayload.ApiResponse;
import com.springboot.mission.global.apiPayload.code.BaseErrorCode;
import com.springboot.mission.global.apiPayload.code.GeneralErrorCode;
import com.springboot.mission.global.apiPayload.exception.ProjectException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionAdvice {
    @ExceptionHandler(ProjectException.class)
    public ResponseEntity<ApiResponse<Void>> handleUserException(ProjectException e) {
        BaseErrorCode errorCode = e.getErrorCode();
        return ResponseEntity.status(errorCode.getStatus())
                .body(ApiResponse.onFailure(errorCode, null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception ex) {
        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(code, ex.getMessage()));
    }
}
