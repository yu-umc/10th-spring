package com.springboot.mission.domain.review.dto;

import lombok.Builder;

import java.util.List;

public class ReviewResponseDTO {
    @Builder
    public record GetReviewInfo(
            Long review_id,
            String store_name,
            String author,
            Integer star,
            String content,
            String photo
    ){}

    @Builder
    public record ReviewListResponse(
            List<GetReviewInfo> content,
            Integer total_mission,
            Integer page_offset
    ) {}
}
