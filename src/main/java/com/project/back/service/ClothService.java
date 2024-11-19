package com.project.back.service;

import org.springframework.http.ResponseEntity;

import com.project.back.dto.request.cloth.PostClothInfoRequestDto;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.cloth.GetClothInfoResponseDto;
import com.project.back.dto.response.cloth.favorite.GetFavoriteCheckResponseDto;
import com.project.back.dto.response.cloth.favorite.GetFavoriteClothListResponseDto;

public interface ClothService {

    ResponseEntity<ResponseDto> postClothInfo(PostClothInfoRequestDto dto, String userId);

    ResponseEntity<? super GetClothInfoResponseDto> getClothInfo(Integer clothId);

    // ResponseEntity<? super GetClothNumberResponseDto> getClothNumber(String
    // userId);

    // 찜 목록
    ResponseEntity<ResponseDto> postFavorite(String userId, Integer clothId);

    ResponseEntity<? super GetFavoriteClothListResponseDto> getFavoriteList(String userId);

    ResponseEntity<? super GetFavoriteCheckResponseDto> getFavoriteCheck(String userId, Integer clothId);

    ResponseEntity<ResponseDto> deleteFavorite(String userId, Integer clothId);
}
