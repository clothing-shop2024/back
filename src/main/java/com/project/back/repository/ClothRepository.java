package com.project.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.back.entity.ClothEntity;
import com.project.back.repository.resultSet.AdminClothResultSet;

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

    // 관리자페이지 상품관리 전체 보기
    @Query(value = 
        "SELECT " +
        "C.cloth_id as clothId, " +
        "C.cloth_name as clothName, " +
        "C.price as price, " +
        "C.discount_price as discountPrice, " +
        "C.category1 as category1, " +
        "C.category2 as category2, " +
        "C.cloth_date as clothDate, " +
        "CI.cloth_main_image as clothMainImage " +
        "FROM cloth C " +
        "INNER JOIN cloth_image CI ON C.cloth_image_number = CI.cloth_image_number " +
        "ORDER BY C.cloth_date DESC"
    , nativeQuery = true)
    List<AdminClothResultSet> getAdminClothList();

}
