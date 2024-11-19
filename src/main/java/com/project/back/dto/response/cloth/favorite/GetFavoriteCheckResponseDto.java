package com.project.back.dto.response.cloth.favorite;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.back.dto.response.ResponseCode;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.ResponseMessage;
import com.project.back.entity.FavoriteClothEntity;

import lombok.Getter;

@Getter
public class GetFavoriteCheckResponseDto extends ResponseDto {
    // 찜한 옷의 아이디와 유저아이디 상품번호를 저장
    private String userId;
    private Integer clothId;
    private String clothDetailNumber;

    private GetFavoriteCheckResponseDto(FavoriteClothEntity favoriteClothEntity) throws Exception {

        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        this.userId = favoriteClothEntity.getUserId();
        this.clothId = favoriteClothEntity.getClothId();
        this.clothDetailNumber = favoriteClothEntity.getClothDetailNumber();
    }

    public static ResponseEntity<GetFavoriteCheckResponseDto> success(FavoriteClothEntity favoriteClothEntity)
            throws Exception {
        GetFavoriteCheckResponseDto responseBody = new GetFavoriteCheckResponseDto(favoriteClothEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
