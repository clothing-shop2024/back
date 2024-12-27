package com.project.back.dto.response.cloth;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.back.common.object.ClothStockListItem;
import com.project.back.dto.response.ResponseCode;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.ResponseMessage;
import com.project.back.repository.resultSet.ClothStockResultSet;

import lombok.Getter;

@Getter
public class GetClothStockListResponseDto extends ResponseDto {
    
    private List<ClothStockListItem> clothStockList;

    private GetClothStockListResponseDto (List<ClothStockResultSet> resultSets) throws Exception {

        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        this.clothStockList = ClothStockListItem.getList(resultSets);
    }

    public static ResponseEntity<GetClothStockListResponseDto> success (List<ClothStockResultSet> clothStockResultSets) throws Exception {

        GetClothStockListResponseDto responseBody = new GetClothStockListResponseDto(clothStockResultSets);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
