package com.project.back.dto.response.cloth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.back.dto.response.ResponseCode;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.ResponseMessage;
import com.project.back.entity.ClothEntity;

import lombok.Getter;

@Getter
public class GetClothIdResponseDto extends ResponseDto {
    private Integer clothId;
    // private String clothNumber;

    private GetClothIdResponseDto(ClothEntity clothEntity) throws Exception {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.clothId = clothEntity.getClothId();
        // this.clothNumber = clothEntity.getClothNumber();
    }

    public static ResponseEntity<GetClothIdResponseDto> success(ClothEntity clothEntity) throws Exception {
        GetClothIdResponseDto responseBody = new GetClothIdResponseDto(clothEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
