package com.project.back.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.back.repository.resultSet.AdminClothResultSet;

import lombok.Getter;

@Getter
public class AdminClothListItem {
    
    private String clothId;
    private String clothName;
    private String category1;
    private String category2;
    private String clothMainImage;
    private Integer price;
    private Integer discountPrice;
    private String clothDate;
    private Integer viewCount;
    private Integer reviewCount;
    private Integer favoriteCount;
    private Double ratingAvg;

    public AdminClothListItem(AdminClothResultSet resultSet) throws Exception {

        this.clothId = resultSet.getClothId();
        this.clothName = resultSet.getClothName();
        this.category1 = resultSet.getCategory1();
        this.category2 = resultSet.getCategory2();
        this.clothMainImage = resultSet.getClothMainImage();
        this.price = resultSet.getPrice();
        this.discountPrice = resultSet.getDiscountPrice();
        this.clothDate = resultSet.getClothDate();
        this.viewCount = resultSet.getViewCount();
        this.reviewCount = resultSet.getReviewCount();
        this.favoriteCount = resultSet.getFavoriteCount();
        this.ratingAvg = resultSet.getRatingAvg();
    }

    public static List<AdminClothListItem> getList(List<AdminClothResultSet> resultSets) throws Exception {
        
        List<AdminClothListItem> adminClothList = new ArrayList<>();

        for (AdminClothResultSet resultSet : resultSets) {
            AdminClothListItem item = new AdminClothListItem(resultSet);
            adminClothList.add(item);
        }

        return adminClothList;
    }
}
