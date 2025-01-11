package com.project.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.back.entity.ClothEntity;
import com.project.back.repository.resultSet.AdminClothResultSet;
import com.project.back.repository.resultSet.ClothResultSet;
import com.project.back.repository.resultSet.ClothStockResultSet;

@Repository
public interface ClothRepository extends JpaRepository<ClothEntity, String> {
    
    ClothEntity findByClothId(String clothId);

    @Query(value =
        "SELECT " +
        "C.cloth_id AS clothId, " +
        "C.cloth_name AS clothName, " +
        "C.category1, C.category2, C.price, " +
        "C.discount_price AS discountPrice, " +
        "C.cloth_date AS clothDate, " +
        "C.view_count AS viewCount, " +
        "C.rating_avg AS ratingAvg, " +
        "C.review_count AS reviewCount, " +
        "C.favorite_count AS favoriteCount, " +
        "CI.cloth_main_image AS clothMainImage " +
        "FROM cloth C " +
        "INNER JOIN cloth_image CI ON C.cloth_image_number = CI.cloth_image_number " +
        "ORDER BY C.cloth_number DESC"
    , nativeQuery = true)
    List<ClothResultSet> findByOrderByClothNumberDesc();

    @Query(value =
        "SELECT " +
        "C.cloth_id AS clothId, " +
        "C.cloth_name AS clothName, " +
        "C.category1, C.category2, C.price, " +
        "C.discount_price AS discountPrice, " +
        "C.cloth_date AS clothDate, " +
        "C.view_count AS viewCount, " +
        "C.rating_avg AS ratingAvg, " +
        "C.review_count AS reviewCount, " +
        "C.favorite_count AS favoriteCount, " +
        "CI.cloth_main_image AS clothMainImage " +
        "FROM cloth C " +
        "INNER JOIN cloth_image CI ON C.cloth_image_number = CI.cloth_image_number " +
        "WHERE C.cloth_name = :clothName " +
        "ORDER BY C.cloth_number DESC"
    , nativeQuery = true)
    List<ClothResultSet> findByClothNameContainsOrderByClothNumberDesc(String clothName);

    @Query(value =
        "SELECT " +
        "C.cloth_id AS clothId, " +
        "C.cloth_name AS clothName, " +
        "C.category1, C.category2, C.price, " +
        "C.discount_price AS discountPrice, " +
        "C.cloth_date AS clothDate, " +
        "C.view_count AS viewCount, " +
        "C.rating_avg AS ratingAvg, " +
        "C.review_count AS reviewCount, " +
        "C.favorite_count AS favoriteCount, " +
        "CI.cloth_main_image AS clothMainImage " +
        "FROM cloth C " +
        "INNER JOIN cloth_image CI ON C.cloth_image_number = CI.cloth_image_number " +
        "WHERE C.category1 = :category1 " +
        "ORDER BY C.cloth_number DESC"
    , nativeQuery = true)
    List<ClothResultSet> findByCategory1ContainsOrderByClothNumberDesc(String category1);

    @Query(value =
        "SELECT " +
        "C.cloth_id AS clothId, " +
        "C.cloth_name AS clothName, " +
        "C.category1, C.category2, C.price, " +
        "C.discount_price AS discountPrice, " +
        "C.cloth_date AS clothDate, " +
        "C.view_count AS viewCount, " +
        "C.rating_avg AS ratingAvg, " +
        "C.review_count AS reviewCount, " +
        "C.favorite_count AS favoriteCount, " +
        "CI.cloth_main_image AS clothMainImage " +
        "FROM cloth C " +
        "INNER JOIN cloth_image CI ON C.cloth_image_number = CI.cloth_image_number " +
        "WHERE C.category2 = :category2 " +
        "ORDER BY C.cloth_number DESC"
    , nativeQuery = true)
    List<ClothResultSet> findByCategory2ContainsOrderByClothNumberDesc(String category2);

    @Query(value =
        "SELECT " +
        "C.cloth_id AS clothId, " +
        "C.cloth_name AS clothName, " +
        "C.category1, C.category2, C.price, " +
        "C.discount_price AS discountPrice, " +
        "C.cloth_date AS clothDate, " +
        "C.view_count AS viewCount, " +
        "C.rating_avg AS ratingAvg, " +
        "C.review_count AS reviewCount, " +
        "C.favorite_count AS favoriteCount, " +
        "CI.cloth_main_image AS clothMainImage " +
        "FROM cloth C " +
        "INNER JOIN cloth_image CI ON C.cloth_image_number = CI.cloth_image_number " +
        "WHERE C.category1 = :category1 " +
        "AND C.cloth_name = :clothName " +
        "ORDER BY C.cloth_number DESC"
    , nativeQuery = true)
    List<ClothResultSet> findByCategory1AndClothNameContainsOrderByClothNumberDesc(String category1, String clothName);

    @Query(value =
        "SELECT " +
        "C.cloth_id AS clothId, " +
        "C.cloth_name AS clothName, " +
        "C.category1, C.category2, C.price, " +
        "C.discount_price AS discountPrice, " +
        "C.cloth_date AS clothDate, " +
        "C.view_count AS viewCount, " +
        "C.rating_avg AS ratingAvg, " +
        "C.review_count AS reviewCount, " +
        "C.favorite_count AS favoriteCount, " +
        "CI.cloth_main_image AS clothMainImage " +
        "FROM cloth C " +
        "INNER JOIN cloth_image CI ON C.cloth_image_number = CI.cloth_image_number " +
        "WHERE C.category2 = :category2 " +
        "AND C.cloth_name = :clothName " +
        "ORDER BY C.cloth_number DESC"
    , nativeQuery = true)
    List<ClothResultSet> findByCategory2AndClothNameContainsOrderByClothNumberDesc(String category2, String clothName);

    @Query(value =
        "SELECT " +
        "C.cloth_id AS clothId, " +
        "C.cloth_name AS clothName, " +
        "C.category1, C.category2, C.price, " +
        "C.discount_price AS discountPrice, " +
        "C.cloth_date AS clothDate, " +
        "C.view_count AS viewCount, " +
        "C.rating_avg AS ratingAvg, " +
        "C.review_count AS reviewCount, " +
        "C.favorite_count AS favoriteCount, " +
        "CI.cloth_main_image AS clothMainImage " +
        "FROM cloth C " +
        "INNER JOIN cloth_image CI ON C.cloth_image_number = CI.cloth_image_number " +
        "ORDER BY C.view_count DESC"
    , nativeQuery = true)
    // Cloth 조회순
    List<ClothResultSet> findByOrderByViewCountDesc();

    // Cloth 카테고리 필터로 가격 낮은순, 가격 높은순, 후기순 리스트 보기
    @Query(value =
        "SELECT " +
        "C.cloth_id AS clothId, " +
        "C.cloth_name AS clothName, " +
        "C.category1, C.category2, C.price, " +
        "C.discount_price AS discountPrice, " +
        "C.cloth_date AS clothDate, " +
        "C.view_count AS viewCount, " +
        "C.rating_avg AS ratingAvg, " +
        "C.review_count AS reviewCount, " +
        "C.favorite_count AS favoriteCount, " +
        "CI.cloth_main_image AS clothMainImage " +
        "FROM cloth C " +
        "INNER JOIN cloth_image CI ON C.cloth_image_number = CI.cloth_image_number " +
        "WHERE C.category1 = :category1 " +
        "ORDER BY C.price DESC"
    , nativeQuery = true)
    List<ClothResultSet> findByCategory1ContainsOrderByPriceDesc(String category1);
    
    @Query(value =
        "SELECT " +
        "C.cloth_id AS clothId, " +
        "C.cloth_name AS clothName, " +
        "C.category1, C.category2, C.price, " +
        "C.discount_price AS discountPrice, " +
        "C.cloth_date AS clothDate, " +
        "C.view_count AS viewCount, " +
        "C.rating_avg AS ratingAvg, " +
        "C.review_count AS reviewCount, " +
        "C.favorite_count AS favoriteCount, " +
        "CI.cloth_main_image AS clothMainImage " +
        "FROM cloth C " +
        "INNER JOIN cloth_image CI ON C.cloth_image_number = CI.cloth_image_number " +
        "WHERE C.category1 = :category1 " +
        "ORDER BY C.price ASC"
    , nativeQuery = true)
    List<ClothResultSet> findByCategory1ContainsOrderByPriceAsc(String category1);
    // List<ClothEntity> findByOrderByReviewCount();

    // Cloth 카테고리2 필터로 가격 낮은순, 가격 높은순, 후기순 리스트 보기
    @Query(value =
        "SELECT " +
        "C.cloth_id AS clothId, " +
        "C.cloth_name AS clothName, " +
        "C.category1, C.category2, C.price, " +
        "C.discount_price AS discountPrice, " +
        "C.cloth_date AS clothDate, " +
        "C.view_count AS viewCount, " +
        "C.rating_avg AS ratingAvg, " +
        "C.review_count AS reviewCount, " +
        "C.favorite_count AS favoriteCount, " +
        "CI.cloth_main_image AS clothMainImage " +
        "FROM cloth C " +
        "INNER JOIN cloth_image CI ON C.cloth_image_number = CI.cloth_image_number " +
        "WHERE C.category2 = :category2 " +
        "ORDER BY C.price DESC"
    , nativeQuery = true)
    List<ClothResultSet> findByCategory2ContainsOrderByPriceDesc(String category2);

    @Query(value =
        "SELECT " +
        "C.cloth_id AS clothId, " +
        "C.cloth_name AS clothName, " +
        "C.category1, C.category2, C.price, " +
        "C.discount_price AS discountPrice, " +
        "C.cloth_date AS clothDate, " +
        "C.view_count AS viewCount, " +
        "C.rating_avg AS ratingAvg, " +
        "C.review_count AS reviewCount, " +
        "C.favorite_count AS favoriteCount, " +
        "CI.cloth_main_image AS clothMainImage " +
        "FROM cloth C " +
        "INNER JOIN cloth_image CI ON C.cloth_image_number = CI.cloth_image_number " +
        "WHERE C.category2 = :category2 " +
        "ORDER BY C.price ASC"
    , nativeQuery = true)
    List<ClothResultSet> findByCategory2ContainsOrderByPriceAsc(String category2);

    @Query(value =
        "SELECT " +
        "C.cloth_id AS clothId, " +
        "C.cloth_name AS clothName, " +
        "C.category1, C.category2, C.price, " +
        "C.discount_price AS discountPrice, " +
        "C.cloth_date AS clothDate, " +
        "C.view_count AS viewCount, " +
        "C.rating_avg AS ratingAvg, " +
        "C.review_count AS reviewCount, " +
        "C.favorite_count AS favoriteCount, " +
        "CI.cloth_main_image AS clothMainImage " +
        "FROM cloth C " +
        "INNER JOIN cloth_image CI ON C.cloth_image_number = CI.cloth_image_number " +
        "WHERE C.category1 = :category1 " +
        "ORDER BY C.view_count DESC"
    , nativeQuery = true)
    List<ClothResultSet> findByCategory1ContainsOrderByViewCountDesc(String category1);

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
