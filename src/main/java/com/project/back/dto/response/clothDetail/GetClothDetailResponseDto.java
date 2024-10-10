package com.project.back.dto.response.clothDetail;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.back.dto.response.ResponseCode;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.ResponseMessage;
import com.project.back.entity.ClothDetailEntity;

import lombok.Getter;

// 옷 상세 테이블의 상세페이지 보기
@Getter
public class GetClothDetailResponseDto extends ResponseDto {
    
    private String clothDetailNumber;
    private String clothDetailName;
    private String clothCategory1;
    private String clothCategory2;
    private Integer price;
    private Integer discountPrice;
    private Integer viewCount;
    private Integer reviewNumber;
    private Integer favoriteNumber;
    private Double ratingAvg;
    private String clothImage1;
    private String clothImage2;

    private GetClothDetailResponseDto (ClothDetailEntity clothDetailEntities) throws Exception {
        
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        this.clothDetailNumber = clothDetailEntities.getClothDetailNumber();
        this.clothDetailName = clothDetailEntities.getClothDetailName();
        this.clothCategory1 = clothDetailEntities.getClothCategory1();
        this.clothCategory2 = clothDetailEntities.getClothCategory2();
        this.price = clothDetailEntities.getPrice();
        this.discountPrice = clothDetailEntities.getDiscountPrice();
        this.viewCount = clothDetailEntities.getViewCount();
        this.reviewNumber = clothDetailEntities.getReviewNumber();
        this.favoriteNumber = clothDetailEntities.getFavoriteNumber();
        this.ratingAvg = clothDetailEntities.getRatingAvg();
        this.clothImage1 = clothDetailEntities.getClothImage1();
        this.clothImage2 = clothDetailEntities.getClothImage2();
        
    }

    public static ResponseEntity<GetClothDetailResponseDto> success(ClothDetailEntity clothDetailEntities) throws Exception {

        GetClothDetailResponseDto responseBody = new GetClothDetailResponseDto(clothDetailEntities);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        
    }
}
