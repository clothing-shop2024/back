package com.project.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.back.entity.ClothEntity;
import com.project.back.repository.resultSet.GetClothFavoriteItemResultSet;

@Repository
public interface ClothRepository extends JpaRepository<ClothEntity, Integer> {
    boolean existsByClothId(Integer clothId);

    ClothEntity findByClothId(Integer clothId);

    @Query(value = "SELECT "
            + "r.cloth_number as clothNumber, "
            + "r.cloth_id as clothId, "
            + "r.cloth_image as clothImage, "
            + "r.cloth_name as clothName, "
            + "r.cloth_category as clothCategory "
            + "FROM cloth r "
            + "WHERE cloth_number "
            + "IN "
            + "(SELECT favorite_cloth_number FROM favorite_cloth WHERE `user_id` = :userId)"
            + "ORDER BY r.restaurant_id ", nativeQuery = true)
    List<GetClothFavoriteItemResultSet> getFavoriteList(@Param("userId") String favoriteUserId);

}
