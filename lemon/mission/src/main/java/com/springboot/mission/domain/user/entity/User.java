package com.springboot.mission.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UUID")
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false) // 소셜에서 생년월일을 제공하지 않을 수도 있다면 false 제거 검토
    private LocalDate birth;

    @Column
    private Integer gender; // 1: 남성, 2: 여성, 0: 기타

    @Column(nullable = false, length = 255)
    private String address;

    @Column(name = "is_deleted", nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;

    @Column(nullable = false, length = 255)
    private String social; // 예: "KAKAO", "GOOGLE", "NAVER"

    @Column(nullable = false, length = 255, unique = true)
    private String mail; // 소셜에서 제공하는 이메일 혹은 고유 식별값(ID)

    @Column(name = "phone_number", length = 255)
    private String phoneNumber;

    @Column(nullable = false, length = 255)
    private String nickname;
}
