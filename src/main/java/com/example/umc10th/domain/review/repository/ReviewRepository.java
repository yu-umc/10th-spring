package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository   // ← 이 어노테이션 있는지 확인!
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
