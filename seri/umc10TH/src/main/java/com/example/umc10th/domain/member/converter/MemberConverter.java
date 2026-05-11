package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberResDTO;

public class MemberConverter {

    public static MemberResDTO.RequestBody toRequestBody(
            String stringTest,
            Long longTest
    ){
        return MemberResDTO.RequestBody.builder()
                .stringTest(stringTest)
                .longTest(longTest)
                .build();

    }

    public static MemberResDTO.GetInfo toGetInfo(
            Member member
    ){
        return MemberResDTO.GetInfo.builder()
                .email(member.getEmail())
                .name(member.getName())
                .point(member.getPoint())
                .phoneNumber(member.getPhoneNumber())
                .profileUrl(member.getPRofileUrl())
                .build();
    }
}
