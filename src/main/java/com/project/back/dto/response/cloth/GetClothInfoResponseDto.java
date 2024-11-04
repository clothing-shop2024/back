package com.project.back.dto.response.cloth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.back.dto.response.ResponseCode;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.ResponseMessage;
import com.project.back.entity.ClothColorEntity;
import com.project.back.entity.ClothEntity;
import com.project.back.entity.ClothSizeEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetClothInfoResponseDto extends ResponseDto {

    private String clothNumber; // 상품ID (고유식별코드)
    private Integer stock; // 재고 수량
    private Integer sizeNumber; // 사이즈 번호
    private String sizeName; // 사이즈 이름
    private Integer colorNumber; // 색상 번호
    private String colorName; // 색상 이름
    // private String couponCode; // 쿠폰
    // private Integer quantity; // 수량

    private GetClothInfoResponseDto(ClothEntity clothEntity, ClothSizeEntity clothSizeEntity,
            ClothColorEntity clothColorEntity) throws Exception {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        this.clothNumber = clothEntity.getClothNumber();
        this.stock = clothEntity.getStock();
        // this.couponCode = clothEntity.getCouponCode(); // 쿠폰코드
        // this.quantity = quantity; // 수량

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
