package com.project.back.dto.response.cloth;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.back.common.object.AdminClothListItem;
import com.project.back.dto.response.ResponseCode;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.ResponseMessage;
import com.project.back.repository.resultSet.AdminClothResultSet;

import lombok.Getter;

@Getter
public class GetAdminClothListResponseDto extends ResponseDto {

    private List<AdminClothListItem> adminClothList;

    private GetAdminClothListResponseDto(List<AdminClothResultSet> resultSets) throws Exception {

        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        this.adminClothList = AdminClothListItem.getList(resultSets);
    }

    public static ResponseEntity<GetAdminClothListResponseDto> success (List<AdminClothResultSet> resultSets) throws Exception {

        GetAdminClothListResponseDto responseBody = new GetAdminClothListResponseDto(resultSets);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
