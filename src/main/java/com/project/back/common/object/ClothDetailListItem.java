package com.project.back.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.back.common.util.ChangeDateFormatUtil;
import com.project.back.entity.ClothDetailEntity;

import lombok.Getter;

@Getter
public class ClothDetailListItem {
    
    private String clothDetailNumber;
    private String clothDetailName;
    private String clothCategory1;
    private String clothCategory2;
    private Integer price;
    private Integer discountPrice;
    private String clothDate;
    private Integer viewCount;
    private Integer reviewCount;
    private Integer favoriteCount;
    private Double ratingAvg;
    private String clothImage1;
    private String clothImage2;

    private ClothDetailListItem(ClothDetailEntity clothDetailEntity) throws Exception {

        String clothDate = ChangeDateFormatUtil.changeYYYYMMDD(clothDetailEntity.getClothDate());

        this.clothDetailNumber = clothDetailEntity.getClothDetailNumber();
        this.clothDetailName = clothDetailEntity.getClothDetailName();
        this.clothCategory1 = clothDetailEntity.getClothCategory1();
        this.clothCategory2 = clothDetailEntity.getClothCategory2();
        this.price = clothDetailEntity.getPrice();
        this.discountPrice = clothDetailEntity.getDiscountPrice();
        this.clothDate = clothDate;
        this.viewCount = clothDetailEntity.getViewCount();
        this.reviewCount = clothDetailEntity.getReviewCount();
        this.favoriteCount = clothDetailEntity.getFavoriteCount();
        this.ratingAvg = clothDetailEntity.getRatingAvg();
        this.clothImage1 = clothDetailEntity.getClothImage1();
        this.clothImage2 = clothDetailEntity.getClothImage2();

    }

    public static List<ClothDetailListItem> getList (List<ClothDetailEntity> clothDetailEntities) throws Exception {

        List<ClothDetailListItem> clothDetailList = new ArrayList<>();

        for (ClothDetailEntity clothDetailEntity : clothDetailEntities) {

            ClothDetailListItem clothDetailListItem = new ClothDetailListItem(clothDetailEntity);
            clothDetailList.add(clothDetailListItem);

        }

        return clothDetailList;
    }
}
