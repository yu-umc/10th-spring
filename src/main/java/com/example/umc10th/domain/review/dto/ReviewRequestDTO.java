// ReviewRequestDTO.java — 요청 받을 때
package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class ReviewRequestDTO {

    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    @NotNull(message = "점수는 필수입니다.")
    @DecimalMin("0.0") @DecimalMax("5.0")
    private Float score;

    @NotBlank(message = "내용은 필수입니다.")
    private String body;
}
