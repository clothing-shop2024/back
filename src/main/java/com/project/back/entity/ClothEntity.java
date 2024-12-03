package com.project.back.entity;

import com.project.back.dto.request.cloth.PatchClothInfoRequestDto;
import com.project.back.dto.request.cloth.PostClothInfoRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "cloth")
@Table(name = "cloth")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClothEntity {

    @Id
    private Integer clothId;
    private Integer stock;

    private String clothImage;
    private String clothCategory;
    private String clothName;
    private String clothFeatures;
    private String clothSnsAddress;
    private String clothNumber;
    private String colorName;
    private Integer colorNumber;
    private String sizeName;
    private Integer sizeNumber;
    private Integer price;
    private Integer discountPrice;

    @ManyToOne
    @JoinColumn(name = "cloth_detail_number")
    private ClothDetailEntity clothDetailNumber;

    public ClothEntity(PostClothInfoRequestDto dto, String userId, UserEntity userEntity) {

        this.clothImage = dto.getClothImage();
        this.clothCategory = dto.getClothCategory();
        this.clothName = dto.getClothName();
        // 특징
        this.clothFeatures = dto.getClothFeatures();
        this.clothSnsAddress = dto.getClothSnsAddress();
        this.clothNumber = dto.getClothNumber();
        this.colorName = dto.getColorName();
        this.colorNumber = dto.getColorNumber();
        this.sizeName = dto.getSizeName();
        this.sizeNumber = dto.getSizeNumber();
        this.price = dto.getPrice();
        this.discountPrice = dto.getDiscountPrice();

    }

    public void updateClothInfo(PatchClothInfoRequestDto dto) {
        this.clothImage = dto.getClothImage();
        this.clothCategory = dto.getClothCategory();
        this.clothName = dto.getClothName();
        // 특징
        this.clothFeatures = dto.getClothFeatures();
        this.clothSnsAddress = dto.getClothSnsAddress();
        this.clothNumber = dto.getClothNumber();
        this.colorName = dto.getColorName();
        this.colorNumber = dto.getColorNumber();
        this.sizeName = dto.getSizeName();
        this.sizeNumber = dto.getSizeNumber();
        this.price = dto.getPrice();
        this.discountPrice = dto.getDiscountPrice();
    }
}
