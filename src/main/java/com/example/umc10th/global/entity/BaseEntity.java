package com.example.umc10th.global.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass // 이 클래스를 상속받는 엔티티들이 아래 필드들을 컬럼으로 인식하게 함
@EntityListeners(AuditingEntityListener.class) // 생성/수정 시간을 자동으로 기록하는 기능 활성화
@Getter
public abstract class BaseEntity {

    @CreatedDate
    @Column(updatable = false) // 생성일은 수정되지 않도록 설정
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}