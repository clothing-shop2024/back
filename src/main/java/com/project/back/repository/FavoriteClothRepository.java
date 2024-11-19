package com.project.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.back.entity.FavoriteClothEntity;

public interface FavoriteClothRepository extends JpaRepository<FavoriteClothEntity, Integer> {
    boolean existsByUserIdAndClothId(String userId, Integer clothId);

    FavoriteClothEntity findByUserIdAndClothId(String userId, Integer clothId);

    void deleteByUserId(String userId);
}
