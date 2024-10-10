package com.project.back.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.back.entity.ClothEntity;

import lombok.Getter;

@Getter
public class ClothListItem {
    
    private String clothNumber;
    private String clothName;
    private String clothDate;
    private Integer stock;
    private String clothDetailNumber;

    private ClothListItem(ClothEntity clothEntity) throws Exception {

        this.clothNumber = clothEntity.getClothNumber();
        this.clothName = clothEntity.getClothName();
        this.clothDate = clothEntity.getClothDate();
        this.stock = clothEntity.getStock();
        this.clothDetailNumber = clothEntity.getClothDetailNumber();

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
