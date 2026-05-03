package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewRequestDTO;
import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.domain.review.entity.Review;                        // ← Review
import com.example.umc10th.domain.review.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;          // ← ReviewRepository
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewRepository reviewRepository;

    // 리뷰 저장
    @PostMapping("/")
    public ApiResponse<ReviewResponseDTO> saveReview(
            @Valid @RequestBody ReviewRequestDTO request) {

        Review review = new Review();
        review.setTitle(request.getTitle());
        review.setScore(request.getScore());
        review.setBody(request.getBody());
        Review saved = reviewRepository.save(review);

        return ApiResponse.of(ReviewSuccessCode.REVIEW_CREATED,
                ReviewResponseDTO.builder()
                        .id(saved.getId())
                        .title(saved.getTitle())
                        .score(saved.getScore())
                        .body(saved.getBody())
                        .build());
    }

    // 리뷰 전체 조회
    @GetMapping("/")
    public ApiResponse<List<ReviewResponseDTO>> getAllReviews() {
        List<ReviewResponseDTO> list = reviewRepository.findAll().stream()
                .map(r -> ReviewResponseDTO.builder()
                        .id(r.getId()).title(r.getTitle())
                        .score(r.getScore()).body(r.getBody())
                        .build())
                .collect(Collectors.toList());

        return ApiResponse.of(ReviewSuccessCode.REVIEW_LIST_OK, list);
    }
}
