package com.project.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.back.entity.ClothDetailEntity;

@Repository
public interface ClothDetailRepository extends JpaRepository<ClothDetailEntity, String> {
    
    ClothDetailEntity findByClothDetailNumber(String clothDetailNumber);

    List<ClothDetailEntity> findByOrderByClothDetailSequenceDesc();

    List<ClothDetailEntity> findByClothDetailNameContainsOrderByClothDetailSequenceDesc(String clothDetailName);

    List<ClothDetailEntity> findByClothCategory1ContainsOrderByClothDetailSequenceDesc(String clothCategory1);

    List<ClothDetailEntity> findByClothCategory2ContainsOrderByClothDetailSequenceDesc(String clothCategory2);

    List<ClothDetailEntity> findByClothCategory1AndClothDetailNameContainsOrderByClothDetailSequenceDesc(String clothCategory1, String clothDetailName);

    List<ClothDetailEntity> findByClothCategory2AndClothDetailNameContainsOrderByClothDetailSequenceDesc(String clothCategory2, String clothDetailName);

    List<ClothDetailEntity> findByOrderByViewCountDesc();

    List<ClothDetailEntity> findByClothCategory1ContainsOrderByViewCountDesc(String clothCategory1);

}
