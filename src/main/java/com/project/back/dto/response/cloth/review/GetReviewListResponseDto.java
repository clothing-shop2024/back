package com.project.back.dto.response.cloth.review;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.back.common.object.ClothReviewListItem;
import com.project.back.dto.response.ResponseCode;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.ResponseMessage;
import com.project.back.repository.resultSet.GetClothReviewListItemResultSet;

import lombok.Getter;

@Getter
public class GetReviewListResponseDto extends ResponseDto {
    private List<ClothReviewListItem> clothReviewList;

    private GetReviewListResponseDto(List<GetClothReviewListItemResultSet> reviewEntities) throws Exception {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.clothReviewList = ClothReviewListItem.getList(reviewEntities);
    }

    public static ResponseEntity<GetReviewListResponseDto> success(
            List<GetClothReviewListItemResultSet> getClothReviewListItemResultSets) throws Exception {
        GetReviewListResponseDto responseBody = new GetReviewListResponseDto(getClothReviewListItemResultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
