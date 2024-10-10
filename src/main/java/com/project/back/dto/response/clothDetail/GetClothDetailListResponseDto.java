package com.project.back.dto.response.clothDetail;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.back.common.object.ClothDetailListItem;
import com.project.back.dto.response.ResponseCode;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.ResponseMessage;
import com.project.back.entity.ClothDetailEntity;

import lombok.Getter;

@Getter
public class GetClothDetailListResponseDto extends ResponseDto {
    
    private List<ClothDetailListItem> clothDetailList;

    private GetClothDetailListResponseDto (List<ClothDetailEntity> ClothDetailEntities) throws Exception {

        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        this.clothDetailList = ClothDetailListItem.getList(ClothDetailEntities);

    }

    public static ResponseEntity<GetClothDetailListResponseDto> success(List<ClothDetailEntity> clothDetailEntities) throws Exception {

        GetClothDetailListResponseDto responseBody = new GetClothDetailListResponseDto(clothDetailEntities);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
