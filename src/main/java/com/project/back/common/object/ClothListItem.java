package com.project.back.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.back.entity.ClothEntity;

import lombok.Getter;

@Getter
public class ClothListItem {
    
    private String clothId;
    private Integer clothNumber;
    private String clothName;
    private String category1;
    private String category2;
    private String clothFeatures;
    private Integer price;
    private Integer discountPrice;
    private Integer clothImageNumber;
    private String clothDate;
    private Integer viewCount;
    private Double ratingAvg;
    private Integer reviewCount;
    private Integer favoriteCount;

    private ClothListItem(ClothEntity clothEntity) throws Exception {

        this.clothId = clothEntity.getClothId();
        this.clothNumber = clothEntity.getClothNumber();
        this.clothName = clothEntity.getClothName();
        this.category1 = clothEntity.getCategory1();
        this.category2 = clothEntity.getCategory2();
        this.clothFeatures = clothEntity.getClothFeatures();
        this.price = clothEntity.getPrice();
        this.discountPrice = clothEntity.getDiscountPrice();
        this.clothImageNumber = clothEntity.getClothImageNumber();
        this.clothDate = clothEntity.getClothDate();
        // this.viewCount = clothEntity.getViewCount();
        // this.ratingAvg = clothEntity.getRatingAvg();
        // this.reviewCount = clothEntity.getReviewCount();
        // this.favoriteCount = clothEntity.getFavoriteCount();
        

    }

    public static List<ClothListItem> getList (List<ClothEntity> clothEntities) throws Exception {

        List<ClothListItem> clothList = new ArrayList<>();

        for (ClothEntity clothEntity : clothEntities) {

            ClothListItem clothListItem = new ClothListItem(clothEntity);
            clothList.add(clothListItem);

        }

        return clothList;
    }
}
