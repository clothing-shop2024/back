package com.project.back.repository.resultSet;

import java.util.List;

import lombok.Getter;

public interface AdminClothDetailResultSet {
    
    String getClothId();
    String getClothName();
    String getCategory1();
    String getCategory2();
    String getClothMainImage();
    Integer getPrice();
    Integer getDiscountPrice();
    String getClothDate();
    Integer getViewCount();
    Double getRatingAvg();
    Integer getReviewCount();
    Integer getFavoriteCount();

}
