package com.example.umc10th.domain.member.dto;

import lombok.Getter;

public class MemberRequestDTO {

    @Getter
    public static  class MyPageRequestDTO {
        private Long id; // 마이페이지를 조회할 유저의 ID
    }

    //만약 회원가입 기능을 만든다면 아래와 같은 클래스도 여기에 추가
    @Getter
    public static class JoinDTO {

    }

}
