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

    // clothDetail 조회순
    List<ClothDetailEntity> findByOrderByViewCountDesc();
    // clothDetail 카테고리 필터로 가격 낮은순, 가격 높은순, 후기순 리스트 보기
    List<ClothDetailEntity> findByClothCategory1ContainsOrderByPriceDesc(String clothCategory1);
    List<ClothDetailEntity> findByClothCategory1ContainsOrderByPriceAsc(String clothCategory1);
    // List<ClothDetailEntity> findByOrderByReviewCount();

    // clothDetail 카테고리2 필터로 가격 낮은순, 가격 높은순, 후기순 리스트 보기
    List<ClothDetailEntity> findByClothCategory2ContainsOrderByPriceDesc(String clothCategory2);
    List<ClothDetailEntity> findByClothCategory2ContainsOrderByPriceAsc(String clothCategory2);

    List<ClothDetailEntity> findByClothCategory1ContainsOrderByViewCountDesc(String clothCategory1);

}
