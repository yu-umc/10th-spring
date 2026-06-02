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
    @Column(name = "UUID") // 기존 설계대로 테이블의 PK 컬럼명을 UUID로 매핑
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false)
    private LocalDate birth;

    @Column
    private Integer gender; // 1: 남성, 2: 여성, 0: 기타

    @Column(nullable = false, length = 255)
    private String address;

    @Column(name = "is_deleted", nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;

    @Column(nullable = false, length = 255)
    private String social; // 예: "KAKAO", "LOCAL"(일반가입) 등

    @Column(nullable = false, length = 255, unique = true)
    private String mail; // 소셜에서 제공하는 고유 식별값(ID) 또는 메일

    @Column(nullable = false, length = 255, unique = true)
    private String email; // 일반 로그인용 아이디(이메일)

    @Column(nullable = false, length = 255)
    private String password; // BCrypt로 암호화되어 저장될 비밀번호

    @Column(name = "phone_number", length = 255)
    private String phoneNumber;

    @Column(nullable = false, length = 255)
    private String nickname;
}
