package com.springboot.mission.domain.user.dto;

import lombok.Builder;

import java.time.LocalDate;

public class UserRequestDTO {
    @Builder
    public record JoinUser(
            String id,
            String password,
            String name,
            LocalDate birthday,
            String gender,
            String address
    ){}
}
