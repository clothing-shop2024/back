package com.project.back.dto.response.cloth;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.back.common.object.ClothListItem;
import com.project.back.dto.response.ResponseCode;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.ResponseMessage;
import com.project.back.entity.ClothEntity;

import lombok.Getter;

@Getter
public class GetClothListResponseDto extends ResponseDto {
    
    private List<ClothListItem> clothList;

    private GetClothListResponseDto(List<ClothEntity> ClothEntities) throws Exception {

        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        this.clothList = ClothListItem.getList(ClothEntities);

    }

    public static ResponseEntity<GetClothListResponseDto> success (List<ClothEntity> ClothEntities) throws Exception {

        GetClothListResponseDto responseBody = new GetClothListResponseDto(ClothEntities);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        
    }
}
