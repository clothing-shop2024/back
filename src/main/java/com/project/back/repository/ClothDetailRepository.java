package com.project.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.back.entity.ClothDetailEntity;

@Repository
public interface ClothDetailRepository extends JpaRepository<ClothDetailEntity, String> {
    
    ClothDetailEntity findByClothDetailNumber(String clothDetailNumber);
    List<ClothDetailEntity> findByOrderByClothDetailSequenceDesc();
}
