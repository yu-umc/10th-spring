package com.example.umc10th.domain.member.dto;

import lombok.Getter;

public class MemberReqDTO {

    //Request Body
    public record RequestBody(
            String stringTest,
            Long longTest
    ){}

    //public static class
    @Getter
    public static class RequestBodyClass{
        private String stringTest;
        private Long longTest;
    }

    public  record GetInfo(
            Long id
    ){}
}
