package com.example.umc10th.domain.review.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter        // ← 이게 있어야 setTitle(), setScore(), setBody() 사용 가능!
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Float score;
    private String body;
}
