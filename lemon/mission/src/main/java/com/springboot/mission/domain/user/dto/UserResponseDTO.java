package com.springboot.mission.domain.user.dto;

import lombok.Builder;

public class UserResponseDTO {
    @Builder
    public record JoinResponse(
            String message
    ){}
}
