package com.springboot.mission.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "store_category")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
