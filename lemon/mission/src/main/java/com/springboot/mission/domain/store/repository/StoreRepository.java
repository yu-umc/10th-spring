package com.springboot.mission.domain.store.repository;

import com.springboot.mission.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
