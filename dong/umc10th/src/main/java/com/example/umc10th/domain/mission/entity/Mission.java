package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.global.apiPayload.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import javax.lang.model.util.Elements;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mission")
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_ID")
    private Long id;

    @Column(name = "deadline")
    private LocalDate deadline; // 미션 기한 (deadline)

    @Column(name = "missionSpec")
    private String missionSpec; // 미션 조건 (mission_Spec)

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private MissionStatus status; // 상태 (status) - Enum 활용

    @Column(name = "point")
    private Long point; // 포인트 (point)

    // 가게ID (store_ID) - 외래키는 객체 관계로 매핑합니다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
}
