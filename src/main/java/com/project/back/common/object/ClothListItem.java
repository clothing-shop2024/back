package com.project.back.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.back.entity.ClothEntity;
import com.project.back.repository.resultSet.GetClothFavoriteItemResultSet;

import lombok.Getter;

@Getter
public class ClothListItem {

    private Integer clothId;
    private String clothNumber;
    private Integer stock;
    private String clothDetailNumber;
    private String clothName;
    private String clothImage;
    private String clothCategory;

    private ClothListItem(ClothEntity clothEntity) throws Exception {

        // 상품 아이디 추가
        this.clothId = clothEntity.getClothId();
        this.clothNumber = clothEntity.getClothNumber();
        this.stock = clothEntity.getStock();
        // this.clothDetailNumber = ClothDetailEntity.getClothDetailNumber();

    }

    public static List<ClothListItem> getList(List<ClothEntity> clothEntities) throws Exception {

        List<ClothListItem> clothList = new ArrayList<>();

        for (ClothEntity clothEntity : clothEntities) {

            ClothListItem clothListItem = new ClothListItem(clothEntity);
            clothList.add(clothListItem);

        }

        return clothList;
    }

    public ClothListItem(GetClothFavoriteItemResultSet getClothFavoriteItemResultSet) throws Exception {

        // 상품 아이디 추가
        this.clothId = getClothFavoriteItemResultSet.getClothId();
        this.clothNumber = getClothFavoriteItemResultSet.getClothNumber();
        this.clothName = getClothFavoriteItemResultSet.getClothName();
        this.clothImage = getClothFavoriteItemResultSet.getClothImage();
        this.clothCategory = getClothFavoriteItemResultSet.getClothCategory();
    }

    public static List<ClothListItem> getFavoriteClothList(
            List<GetClothFavoriteItemResultSet> getClothFavoriteItemResultSetList) throws Exception {
        List<ClothListItem> clothList = new ArrayList<>();

        for (GetClothFavoriteItemResultSet getClothFavoriteItemResultSet : getClothFavoriteItemResultSetList) {
            ClothListItem clothFavoriteListItem = new ClothListItem(getClothFavoriteItemResultSet);
            clothList.add(clothFavoriteListItem);
        }
        return clothList;
    }

}
