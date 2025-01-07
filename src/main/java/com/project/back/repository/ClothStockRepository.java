package com.project.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.back.common.object.ClothStockListItem;
import com.project.back.entity.ClothStockEntity;
import com.project.back.repository.resultSet.AdminClothDetailResultSet;
import com.project.back.repository.resultSet.ClothStockResultSet;

@Repository
public interface ClothStockRepository extends JpaRepository<ClothStockEntity, Integer> {
    
    // @Query(value =
    //     "SELECT " +
    //     "CS.stock_Number AS stockNumber, " +
    //     "CS.stock_date AS stockDate, " +
    //     "CS.quantity AS quantity, " +
    //     "C.cloth_id AS clothId, " +
    //     "C.cloth_name AS clothName, " +
    //     "SZ.size_name AS sizeName, " +
    //     "CL.color_name AS colorName " +
    //     "FROM cloth_stock CS " +
    //     "INNER JOIN cloth C ON CS.cloth_id = C.cloth_id " +
    //     "INNER JOIN cloth_size_map CSM ON CS.size_number = CSM.size_number " +
    //     "INNER JOIN cloth_size SZ ON CSM.size_id = SZ.size_id " +
    //     "INNER JOIN cloth_color_map CCM ON CS.color_number = CCM.color_number " +
    //     "INNER JOIN cloth_color CL ON CCM.color_id = CL.color_id " +
    //     "ORDER BY CS.stock_date ASC, CS.size_number ASC, CS.color_number ASC"
    // , nativeQuery = true)
    // List<ClothStockResultSet> getClothStockList();

    @Query(value = 
        "SELECT " +
        "C.cloth_id AS clothId, " +
        "C.cloth_name AS clothName, " +
        "C.category1, " +
        "C.category2, " +
        "CI.cloth_main_image AS clothMainImage, " +
        "C.price, " +
        "C.discount_price AS discountPrice, " +
        "C.cloth_date AS clothDate, " +
        "C.view_count AS viewCount, " +
        "C.rating_avg AS ratingAvg, " +
        "C.review_count AS reviewCount, " +
        "C.favorite_count AS favoriteCount, " +
        "CS.stock_number AS stocknumber, " +
        "CC.color_name AS colorName, " +
        "CSZ.size_name AS sizeName, " +
        "CS.quantity, " +
        "CS.stock_date AS stockDate " +
        "FROM cloth C " +
        "INNER JOIN cloth_image CI ON C.cloth_image_number = CI.cloth_image_number " +
        "INNER JOIN cloth_color_map CCM ON C.cloth_id = CCM.cloth_id " +
        "INNER JOIN cloth_size_map CSM ON C.cloth_id = CSM.cloth_id " +
        "INNER JOIN cloth_color CC ON CCM.color_number = CC.color_number " +
        "INNER JOIN cloth_size CSZ ON CSM.size_number = CSZ.size_number " +
        "INNER JOIN cloth_stock CS ON CCM.cloth_color_number = CS.cloth_color_number " +
        "    AND CSM.cloth_size_number = CS.cloth_size_number " +
        "WHERE C.cloth_id = :clothId " +
        "ORDER BY CS.stock_date ASC, CSZ.size_number ASC, CC.color_number ASC"
    , nativeQuery = true)
    List<AdminClothDetailResultSet> getAdminClothDetail(String clothId);

    @Query(value =
        "SELECT " +
        "C.cloth_id AS clothId, " +
        "C.cloth_name AS clothName, " +
        "CS.stock_number AS stocknumber, " +
        "CC.color_name AS colorName, " +
        "CSZ.size_name AS sizeName, " +
        "CS.quantity, " +
        "CS.stock_date AS stockDate " +
        "FROM cloth C " +
        "INNER JOIN cloth_color_map CCM ON C.cloth_id = CCM.cloth_id " +
        "INNER JOIN cloth_size_map CSM ON C.cloth_id = CSM.cloth_id " +
        "INNER JOIN cloth_color CC ON CCM.color_number = CC.color_number " +
        "INNER JOIN cloth_size CSZ ON CSM.size_number = CSZ.size_number " +
        "INNER JOIN cloth_stock CS ON CCM.cloth_color_number = CS.cloth_color_number " +
        "AND CSM.cloth_size_number = CS.cloth_size_number " +
        "WHERE C.cloth_id = :clothId " +
        "ORDER BY CS.stock_date ASC, CSZ.size_number ASC, CC.color_number ASC"
    , nativeQuery = true)
    List<ClothStockResultSet> getClothStockList(String clothId);
}
