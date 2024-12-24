package com.project.back.dto.response.cloth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.back.dto.response.ResponseCode;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.ResponseMessage;
import com.project.back.entity.ClothEntity;

import lombok.Getter;

@Getter
public class GetClothResponseDto extends ResponseDto {

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

    private GetClothResponseDto (ClothEntity clothEntities) throws Exception {

        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        this.clothId = clothEntities.getClothId();
        this.clothNumber = clothEntities.getClothNumber();
        this.clothName = clothEntities.getClothName();
        this.category1 = clothEntities.getCategory1();
        this.category2 = clothEntities.getCategory2();
        this.clothFeatures = clothEntities.getClothFeatures();
        this.price = clothEntities.getPrice();
        this.discountPrice = clothEntities.getDiscountPrice();
        this.clothImageNumber = clothEntities.getClothImageNumber();
        this.clothDate = clothEntities.getClothDate();
    }

    public static ResponseEntity<GetClothResponseDto> success(ClothEntity clothEntities) throws Exception {
        GetClothResponseDto responseBody = new GetClothResponseDto(clothEntities);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    
}
