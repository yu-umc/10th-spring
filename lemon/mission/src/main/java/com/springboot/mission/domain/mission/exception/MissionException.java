package com.springboot.mission.domain.mission.exception;

import com.springboot.mission.global.apiPayload.code.BaseErrorCode;
import com.springboot.mission.global.apiPayload.exception.ProjectException;

public class MissionException extends ProjectException {
    public MissionException(BaseErrorCode errorCode) {super(errorCode);}
}
