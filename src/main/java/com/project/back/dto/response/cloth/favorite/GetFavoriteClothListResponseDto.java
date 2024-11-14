package com.project.back.dto.response.cloth.favorite;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.back.common.object.ClothListItem;
import com.project.back.dto.response.ResponseCode;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.ResponseMessage;
import com.project.back.repository.resultSet.GetClothFavoriteItemResultSet;

import lombok.Getter;

@Getter
public class GetFavoriteClothListResponseDto extends ResponseDto {
    private List<ClothListItem> clothFavoriteList;

    private GetFavoriteClothListResponseDto(List<GetClothFavoriteItemResultSet> favoriteClothEntities)
            throws Exception {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.clothFavoriteList = ClothListItem.getFavoriteClothList(favoriteClothEntities);
    }

    public static ResponseEntity<GetFavoriteClothListResponseDto> success(
            List<GetClothFavoriteItemResultSet> getClothFavoriteItemResultSets) throws Exception {
        GetFavoriteClothListResponseDto responseBody = new GetFavoriteClothListResponseDto(
                getClothFavoriteItemResultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
