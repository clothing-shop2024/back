package com.project.back.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.back.repository.resultSet.ClothResultSet;

import lombok.Getter;

@Getter
public class ClothListItem {
    
    private String clothId;
    private String clothName;
    private String category1;
    private String category2;
    private Integer price;
    private Integer discountPrice;
    private Integer clothImageNumber;
    private String clothMainImage;
    private String clothDate;
    private Integer viewCount;
    private Double ratingAvg;
    private Integer reviewCount;
    private Integer favoriteCount;

    private ClothListItem(ClothResultSet resultSet) throws Exception {

        this.clothId = resultSet.getClothId();
        this.clothName = resultSet.getClothName();
        this.category1 = resultSet.getCategory1();
        this.category2 = resultSet.getCategory2();
        this.price = resultSet.getPrice();
        this.discountPrice = resultSet.getDiscountPrice();
        this.clothImageNumber = resultSet.getClothImageNumber();
        this.clothMainImage = resultSet.getClothMainImage();
        this.clothDate = resultSet.getClothDate();
        this.viewCount = resultSet.getViewCount();
        this.ratingAvg = resultSet.getRatingAvg();
        this.reviewCount = resultSet.getReviewCount();
        this.favoriteCount = resultSet.getFavoriteCount();
        

    }

    public static List<ClothListItem> getList (List<ClothResultSet> resultSets) throws Exception {

        List<ClothListItem> clothList = new ArrayList<>();

        for (ClothResultSet resultSet : resultSets) {

            ClothListItem item = new ClothListItem(resultSet);
            clothList.add(item);

        }

        return clothList;
    }
}
