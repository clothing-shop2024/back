package com.project.back.dto.response.cloth;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.back.common.object.ClothListItem;
import com.project.back.dto.response.ResponseCode;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.ResponseMessage;
import com.project.back.repository.resultSet.ClothResultSet;

import lombok.Getter;

@Getter
public class GetClothListResponseDto extends ResponseDto {
    
    private List<ClothListItem> clothList;

    private GetClothListResponseDto(List<ClothResultSet> resultSets) throws Exception {

        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        this.clothList = ClothListItem.getList(resultSets);
    }

    public static ResponseEntity<GetClothListResponseDto> success(List<ClothResultSet> clothResultSets) throws Exception {

        GetClothListResponseDto responseBody = new GetClothListResponseDto(clothResultSets);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
