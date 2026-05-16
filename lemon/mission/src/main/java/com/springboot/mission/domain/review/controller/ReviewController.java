package com.springboot.mission.domain.review.controller;

import com.springboot.mission.domain.review.dto.ReviewRequestDTO;
import com.springboot.mission.domain.review.dto.ReviewResponseDTO;
import com.springboot.mission.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{userId}/review/{storeId}")
    public ResponseEntity<ReviewResponseDTO.ReviewListResponse> createReview(
            @PathVariable(name = "userId") Long userId,
            @PathVariable(name = "storeId") Long storeId,
            @RequestBody ReviewRequestDTO.PostReview request,
            @RequestHeader("Authorization") String token) {

        ReviewResponseDTO.ReviewListResponse response =
                reviewService.postReviewAndGetList(userId, storeId, request, token);

        return ResponseEntity.ok(response);
    }

    /**
     * 내가 작성한 리뷰 목록 페이징 조회 API
     */
    @GetMapping("/{userId}/reviews")
    public ResponseEntity<ReviewResponseDTO.ReviewListResponse> getMyReviews(
            @PathVariable(name = "userId") Long userId,
            @RequestHeader("Authorization") String token,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "sort", defaultValue = "id") String sort) { // 정렬 파라미터 추가

        ReviewResponseDTO.ReviewListResponse response = reviewService.getMyReviewPage(userId, page, sort);
        return ResponseEntity.ok(response);
    }
}
