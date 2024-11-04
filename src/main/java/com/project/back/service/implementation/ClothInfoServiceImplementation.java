package com.project.back.service.implementation;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.back.dto.request.cloth.PostClothInfoRequestDto;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.cloth.GetClothInfoResponseDto;
import com.project.back.entity.ClothEntity;
import com.project.back.repository.ClothInfoRepository;
import com.project.back.repository.UserRepository;
import com.project.back.service.ClothInfoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClothInfoServiceImplementation implements ClothInfoService {

    private final ClothInfoRepository clothInfoRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<ResponseDto> postClothInfo(PostClothInfoRequestDto dto, String userId) {
        try {

            boolean isExistUser = userRepository.existsByUserId(userId);

            // 로그인을 하지 않을 경우 로그인화면으로 이동 기능

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetClothInfoResponseDto> getClothInfo(String clothNumber) {
        try {
            ClothEntity clothEntity = clothInfoRepository.findByClothNumber(clothNumber);

            return GetClothInfoResponseDto.success(clothEntity, null, null);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }
}
