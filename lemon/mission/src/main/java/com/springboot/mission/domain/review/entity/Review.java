package com.springboot.mission.domain.review.entity;

import com.springboot.mission.domain.store.entity.Store;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Column(nullable = false, length = 255)
    private String nickname;

    @Column(nullable = false)
    private Integer star; // INT, NOT NULL 반영

    @Column(name = "review_content", nullable = false, length = 255)
    private String content; // varchar(255), NOT NULL 반영

    @Column(name = "review_photo1", length = 255) // NULL 허용 (Default: NULL)
    private String photo1;

    @Column(name = "review_photo2", length = 255)
    private String photo2;

    @Column(name = "review_photo3", length = 255)
    private String photo3;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store; // 외래키
}
