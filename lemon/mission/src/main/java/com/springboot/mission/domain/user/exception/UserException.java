package com.springboot.mission.domain.user.exception;

import com.springboot.mission.global.apiPayload.code.BaseErrorCode;
import com.springboot.mission.global.apiPayload.exception.ProjectException;

public class UserException extends ProjectException {
    public UserException(BaseErrorCode errorCode) {super(errorCode);}
}
