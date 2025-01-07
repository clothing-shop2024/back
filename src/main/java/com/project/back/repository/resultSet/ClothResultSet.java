package com.project.back.repository.resultSet;

public interface ClothResultSet {
    
    String getClothId();
    String getClothName();
    String getCategory1();
    String getCategory2();
    Integer getPrice();
    Integer getDiscountPrice();
    Integer getClothImageNumber();
    String getClothMainImage();
    String getClothDate();
    Integer getViewCount();
    Double getRatingAvg();
    Integer getReviewCount();
    Integer getFavoriteCount();

}
