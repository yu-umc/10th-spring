package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.global.apiPayload.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "region")
public class Region{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_ID")
    private Long id; // 지역ID (region_ID)

    @Column(name = "region_name", nullable = false, length = 20)
    private String region_name; // 지역 이름 (region_name)
}
