package com.project.back.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.back.entity.ClothEntity;
import com.project.back.entity.ClothDetailEntity;

import lombok.Getter;

@Getter
public class ClothListItem {
    
    private String clothNumber;
    private Integer stock;
    private String clothDetailNumber;

    private ClothListItem(ClothEntity clothEntity) throws Exception {

        this.clothNumber = clothEntity.getClothNumber();
        this.stock = clothEntity.getStock();
        // this.clothDetailNumber = ClothDetailEntity.getClothDetailNumber();

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
