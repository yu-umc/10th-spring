package com.springboot.mission.domain.review.dto;

import com.springboot.mission.domain.review.entity.Review;
import com.springboot.mission.domain.store.entity.Store;
import lombok.Builder;

public class ReviewRequestDTO {
    @Builder
    public record PostReview(
            Integer star,
            String content,
            String photo // 프론트에서 넘겨주는 대표 사진
    ){
        /**
         * DTO -> Entity 변환 (toEntity)
         */
        public Review toEntity(Store store, String userNickname) {
            return Review.builder()
                    .star(this.star)
                    .content(this.content)
                    .photo1(this.photo) // 전달받은 사진을 photo1에 저장
                    .nickname(userNickname)
                    .store(store)
                    .build();
        }
    }
}
