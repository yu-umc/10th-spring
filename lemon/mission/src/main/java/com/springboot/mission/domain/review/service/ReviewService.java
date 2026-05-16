package com.springboot.mission.domain.review.service;

import com.springboot.mission.domain.review.dto.ReviewRequestDTO;
import com.springboot.mission.domain.review.dto.ReviewResponseDTO;
import com.springboot.mission.domain.review.entity.Review;
import com.springboot.mission.domain.review.exception.ReviewException;
import com.springboot.mission.domain.review.repository.ReviewRepository;
import com.springboot.mission.domain.store.entity.Store;
import com.springboot.mission.domain.store.repository.StoreRepository;
import com.springboot.mission.domain.user.entity.User;
import com.springboot.mission.domain.user.repository.UserRepository;
import com.springboot.mission.global.apiPayload.code.GeneralErrorCode;
import com.springboot.mission.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    /**
     * 리뷰를 작성하고, 해당 가게의 최신 리뷰 목록을 반환합니다.
     */
    @Transactional
    public ReviewResponseDTO.ReviewListResponse postReviewAndGetList(
            Long userId, Long storeId, ReviewRequestDTO.PostReview request, String token) {

        // 1. 유저 존재 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ReviewException(GeneralErrorCode.NOT_FOUND));

        // 2. 가게 존재 확인
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ReviewException(GeneralErrorCode.NOT_FOUND));

        // 3. 리뷰 데이터 검증
        if (request.star() <= 0) {
            // MemberErrorCode나 별도의 ReviewErrorCode를 정의하여 사용 가능합니다.
            throw new ReviewException(GeneralErrorCode.BAQ_REQUEST);
        }

        // 4. 저장 및 목록 반환 로직
        Review review = request.toEntity(store, user.getNickname());
        reviewRepository.save(review);

        List<Review> reviews = reviewRepository.findAllByStore(store);

        return ReviewResponseDTO.ReviewListResponse.builder()
                .content(reviews.stream().map(ReviewResponseDTO.GetReviewInfo::from).toList())
                .total_mission(reviews.size())
                .page_offset(0)
                .build();
    }

    /**
     * 특정 사용자가 작성한 리뷰 목록을 정렬 및 페이징하여 조회합니다.
     */
    public ReviewResponseDTO.ReviewListResponse getMyReviewPage(Long userId, int page, String sortProperty) {
        // 1. 유저 존재 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ProjectException(GeneralErrorCode.NOT_FOUND));

        // 2. 정렬 기준 설정 (기본은 id 내림차순 / 최신순)
        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        // "star"가 들어오면 별점 내림차순(높은 순)으로 변경
        if ("star".equalsIgnoreCase(sortProperty)) {
            sort = Sort.by(Sort.Direction.DESC, "star");
        }

        // 3. 정렬 정보가 포함된 PageRequest 생성 (한 페이지당 10개)
        PageRequest pageRequest = PageRequest.of(page, 10, sort);

        // 4. 리포지토리 조회 및 DTO 변환
        Page<Review> reviewPage = reviewRepository.findAllByNickname(user.getNickname(), pageRequest);
        return ReviewResponseDTO.ReviewListResponse.from(reviewPage);
    }
}