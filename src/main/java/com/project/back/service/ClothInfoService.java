package com.project.back.service;

import org.springframework.http.ResponseEntity;

import com.project.back.dto.request.cloth.PostClothInfoRequestDto;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.cloth.GetClothInfoResponseDto;

public interface ClothInfoService {

    ResponseEntity<ResponseDto> postClothInfo(PostClothInfoRequestDto dto, String userId);

    ResponseEntity<? super GetClothInfoResponseDto> getClothInfo(String clothNumber);
}
