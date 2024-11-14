package com.project.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.back.entity.FavoriteClothEntity;

public interface FavoriteClothRepository extends JpaRepository<FavoriteClothEntity, Integer> {
    boolean existsByUserIdAndClothNumber(String userId, Integer clothNumber);

    FavoriteClothEntity findByUserIdAndClothNumber(String userId, Integer clothNumber);

    void deleteByUserId(String userId);
}
