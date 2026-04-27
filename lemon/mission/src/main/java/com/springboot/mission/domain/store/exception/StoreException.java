package com.springboot.mission.domain.store.exception;

import com.springboot.mission.domain.store.entity.Store;
import com.springboot.mission.global.apiPayload.code.BaseErrorCode;
import com.springboot.mission.global.apiPayload.exception.ProjectException;

public class StoreException extends ProjectException {
    public StoreException(BaseErrorCode errorCode) {super(errorCode);}
}
