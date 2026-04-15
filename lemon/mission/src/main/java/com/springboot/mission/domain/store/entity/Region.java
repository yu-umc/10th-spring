package com.springboot.mission.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "local")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "local_id")
    private Long id;

    @Column(name = "local_name", nullable = false, length = 255)
    private String name;
}
