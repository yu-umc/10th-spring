package com.springboot.mission.domain.review.dto;

import com.springboot.mission.domain.review.entity.Review;
import lombok.Builder;
import org.springframework.data.domain.Page;

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
    ) {
        public static GetReviewInfo from(Review review) {
            return GetReviewInfo.builder()
                    .review_id(review.getId())
                    .store_name(review.getStore().getTitle())
                    .author(review.getNickname())
                    .star(review.getStar())
                    .content(review.getContent())
                    .photo(review.getPhoto1())
                    .build();
        }
    }

    @Builder
    public record ReviewListResponse(
            List<GetReviewInfo> content,
            Integer total_mission, // 보통 total_review가 적절하나 DTO 필드명 유지
            Integer page_offset
    ) {
        public static ReviewListResponse from(Page<Review> reviewPage) {
            return ReviewListResponse.builder()
                    .content(reviewPage.stream()
                            .map(GetReviewInfo::from) // GetReviewInfo의 from 메서드 호출
                            .toList())
                    .total_mission((int) reviewPage.getTotalElements()) // long -> Integer 형변환
                    .page_offset(reviewPage.getNumber())                // 현재 페이지 번호 (0부터 시작)
                    .build();
        }
    }
}
