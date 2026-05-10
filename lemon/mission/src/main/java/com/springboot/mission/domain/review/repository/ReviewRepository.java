package com.springboot.mission.domain.review.repository;

import com.springboot.mission.domain.review.entity.Review;
import com.springboot.mission.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 특정 가게의 모든 리뷰를 조회
    List<Review> findAllByStore(Store store);
}
