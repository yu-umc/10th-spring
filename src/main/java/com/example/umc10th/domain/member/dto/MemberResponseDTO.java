package com.example.umc10th.domain.member.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyPageResultDTO {
        private String name;
        private String profileURL;
        private String email;
        private String PhoneNumber;
        private Integer point;
    }
}
