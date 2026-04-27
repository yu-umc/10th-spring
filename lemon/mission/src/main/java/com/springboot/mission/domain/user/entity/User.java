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
    private Long id; // 또는 UUID 타입 사용 시 private UUID id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "gender")
    private Integer gender; // INT 타입 매핑

    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @Column(name = "is_deleted", nullable = false)
    @Builder.Default
    private Boolean isDeleted = false; // TINYINT(1) 매핑, 기본값 false

    @Column(name = "social", nullable = false, length = 255)
    private String social;

    @Column(name = "mail", nullable = false, length = 255)
    private String mail;

    @Column(name = "phone_number", length = 255)
    private String phoneNumber; // NULL 허용

    @Column(name = "nickname", nullable = false, length = 255)
    @Builder.Default
    private String nickname = "사용자"; // 기본값 '사용자'
}
