package com.springboot.mission.domain.mission.entity;

import com.springboot.mission.domain.store.entity.Region;
import com.springboot.mission.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "mission")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Integer score;

    @Column(nullable = false)
    private LocalDate deadline;

    @Column(nullable = false)
    private Integer number; // 구분 번호

    // 외래키 매핑: 가상의 Store 엔티티와 N:1 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    // 외래키 매핑: 가상의 Region 엔티티와 N:1 관계 (명세서의 address_id)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Region region;
}
