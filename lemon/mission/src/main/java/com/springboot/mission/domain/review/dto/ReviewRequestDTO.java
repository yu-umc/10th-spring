package com.springboot.mission.domain.review.dto;

import lombok.Builder;

public class ReviewRequestDTO {
    @Builder
    public record PostReview(
            Integer star,
            String content,
            String photo
    ){}
}
