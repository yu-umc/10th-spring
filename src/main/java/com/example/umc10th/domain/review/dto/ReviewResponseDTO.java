// ReviewResponseDTO.java — 응답 보낼 때
package com.example.umc10th.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewResponseDTO {
    private Long id;
    private String title;
    private Float score;
    private String body;
}
