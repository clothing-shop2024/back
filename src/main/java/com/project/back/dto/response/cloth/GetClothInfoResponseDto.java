package com.project.back.dto.response.cloth;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.back.common.object.ClothReviewListItem;
import com.project.back.dto.response.ResponseCode;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.ResponseMessage;
import com.project.back.entity.ClothColorEntity;
import com.project.back.entity.ClothEntity;
import com.project.back.entity.ClothSizeEntity;

import lombok.Getter;

@Getter
public class GetClothInfoResponseDto extends ResponseDto {

    // private Integer clothId; // 상품ID
    // private Integer stock; // 재고 수량
    // private String couponCode; // 쿠폰
    // private Integer quantity; // 수량

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
    private List<ClothReviewListItem> clothReviewList;

    private GetClothInfoResponseDto(ClothEntity clothEntity, ClothSizeEntity clothSizeEntity,
            ClothColorEntity clothColorEntity) throws Exception {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        // this.clothId = clothEntity.getClothId();
        // this.clothNumber = clothEntity.getClothNumber();
        // this.stock = clothEntity.getStock();
        // this.couponCode = clothEntity.getCouponCode(); // 쿠폰코드
        // this.quantity = quantity; // 수량

        this.clothImage = clothEntity.getClothImage();
        this.clothCategory = clothEntity.getClothCategory();
        this.clothName = clothEntity.getClothName();
        this.clothFeatures = clothEntity.getClothFeatures();
        this.clothSnsAddress = clothEntity.getClothSnsAddress();
        this.clothNumber = clothEntity.getClothNumber();
        this.colorName = clothEntity.getColorName();
        this.sizeName = clothEntity.getSizeName();
        this.price = clothEntity.getPrice();
        this.discountPrice = clothEntity.getDiscountPrice();

        if (clothSizeEntity != null) {
            this.sizeNumber = clothSizeEntity.getSizeNumber();
            this.sizeName = clothSizeEntity.getSizeName();
        }

        if (clothColorEntity != null) {
            this.colorNumber = clothColorEntity.getColorNumber();
            this.colorName = clothColorEntity.getColorName();
        }
    }

    public static ResponseEntity<GetClothInfoResponseDto> success(ClothEntity clothEntity,
            ClothSizeEntity clothSizeEntity, ClothColorEntity clothColorEntity) throws Exception {
        GetClothInfoResponseDto responseBody = new GetClothInfoResponseDto(clothEntity, clothSizeEntity,
                clothColorEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
