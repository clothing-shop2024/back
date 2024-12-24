package com.project.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.back.entity.ClothEntity;

@Repository
public interface ClothRepository extends JpaRepository<ClothEntity, String> {
    
    ClothEntity findByClothId(String clothId);

    List<ClothEntity> findByOrderByClothNumberDesc();

    List<ClothEntity> findByClothNameContainsOrderByClothNumberDesc(String ClothName);

    List<ClothEntity> findByCategory1ContainsOrderByClothNumberDesc(String category1);

    List<ClothEntity> findByCategory2ContainsOrderByClothNumberDesc(String category2);

    List<ClothEntity> findByCategory1AndClothNameContainsOrderByClothNumberDesc(String category1, String ClothName);

    List<ClothEntity> findByCategory2AndClothNameContainsOrderByClothNumberDesc(String category2, String ClothName);

    // Cloth 조회순
    List<ClothEntity> findByOrderByViewCountDesc();
    // Cloth 카테고리 필터로 가격 낮은순, 가격 높은순, 후기순 리스트 보기
    List<ClothEntity> findByCategory1ContainsOrderByPriceDesc(String category1);
    List<ClothEntity> findByCategory1ContainsOrderByPriceAsc(String category1);
    // List<ClothEntity> findByOrderByReviewCount();

    // Cloth 카테고리2 필터로 가격 낮은순, 가격 높은순, 후기순 리스트 보기
    List<ClothEntity> findByCategory2ContainsOrderByPriceDesc(String category2);
    List<ClothEntity> findByCategory2ContainsOrderByPriceAsc(String category2);

    List<ClothEntity> findByCategory1ContainsOrderByViewCountDesc(String category1);

}
