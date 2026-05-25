package com.example.umc10th.domain.review.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestDTO {
    private String title;
    private Float score;
    private String body;
}
