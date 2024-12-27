package com.project.back.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.back.entity.ClothStockEntity;
import com.project.back.repository.resultSet.ClothStockResultSet;

import lombok.Getter;

@Getter
public class ClothStockListItem {
    
    private String clothId;
    private Integer stockNumber;
    // private Integer clothSizeNumber;
    // private Integer clothColorNumber;
    private String colorName;
    private String sizeName;
    private Integer quantity;
    private String stockDate;

    private ClothStockListItem(ClothStockResultSet resultSet) throws Exception {

        this.clothId = resultSet.getClothId();
        this.stockNumber = resultSet.getStockNumber();
        this.sizeName = resultSet.getSizeName();
        this.colorName = resultSet.getColorName();
        // this.clothSizeNumber = clothStockEntity.getClothSizeNumber();
        // this.clothColorNumber = clothStockEntity.getClothColorNumber();
        this.quantity = resultSet.getQuantity();
        this.stockDate = resultSet.getStockDate();
    }

    public static List<ClothStockListItem> getList(List<ClothStockResultSet> resultSets) throws Exception {
        
        List<ClothStockListItem> clothStockList = new ArrayList<>();

        for (ClothStockResultSet resultSet : resultSets) {

            ClothStockListItem item = new ClothStockListItem(resultSet);
            clothStockList.add(item);
        }

        return clothStockList;
    }
}
