package com.example.umc10th.domain.user.dto;

import jakarta.validation.constraints.NotNull;

public class UserReqDTO {

    public record UserIdReqDTO(
        @NotNull(message = "아이디는 필수입니다.")
        Long userId
    ){}
}
