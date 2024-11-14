package com.project.back.dto.response.cloth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.back.dto.response.ResponseCode;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.ResponseMessage;
import com.project.back.entity.ClothEntity;

import lombok.Getter;

@Getter
public class GetClothNumberResponseDto extends ResponseDto {
    private Integer clothNumber;

    private GetClothNumberResponseDto(ClothEntity clothEntity) throws Exception {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.clothNumber = clothEntity.getClothNumber();
    }

    public static ResponseEntity<GetClothNumberResponseDto> success(ClothEntity clothEntity) throws Exception {
        GetClothNumberResponseDto responseBody = new GetClothNumberResponseDto(clothEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
