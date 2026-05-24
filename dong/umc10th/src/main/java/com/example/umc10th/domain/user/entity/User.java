package com.example.umc10th.domain.user.entity;

import com.example.umc10th.domain.user.enums.Certification;
import com.example.umc10th.domain.user.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_ID")
    private Long id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "user_address")
    private String address;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "certification")
    @Enumerated(EnumType.STRING)
    private Certification certification;
}
