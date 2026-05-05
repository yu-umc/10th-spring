package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import org.springframework.stereotype.Service;

@Service
public class MissionService {

    // Query Parameter
    public String singleParameter(
            String singleParameter
    ){
        return singleParameter;
    }

    // Request Body
    public MissionResDTO.RequestBody requestBody(
            MissionReqDTO.RequestBody dto
    ){
        return MissionConverter.toRequestBody(dto.stringTest(), dto.longTest());
    }
}
