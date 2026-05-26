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
@Table(name = "store")
public class Store{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_ID")
    private Long id; // 가게ID

    @Column(name = "store_name", nullable = false, length = 50)
    private String store_name; // 이름 (store_name)

    @Column(name = "store_address", nullable = false, length = 100)
    private String address; // 주소 (store_address)

    @Column(name = "storeImage", length = 100)
    private String storeImage; // 사진 (store_image) - URL 형태

    // 지역ID (region_ID) - 외래키 관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;
}