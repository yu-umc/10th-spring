package com.example.umc10th.domain.member.exception;

import com.example.umc10th.global.apiPayload.exception.ProjectException;

public class MemberException extends ProjectException {
    public MemberException(String message) {
        super(errorCode);
    }
}
