package com.project.back.service.implementation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.clothDetail.GetClothDetailListResponseDto;
import com.project.back.entity.ClothDetailEntity;
import com.project.back.repository.ClothDetailRepository;
import com.project.back.service.ClothDetailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClothDetailServiceImplementation implements ClothDetailService {

    private final ClothDetailRepository clothDetailRepository;
    
    
    @Override
    // 옷 상세 테이블 전체 리스트 보기
    public ResponseEntity<? super GetClothDetailListResponseDto> getClothDetailList() {
        
        try {

            List<ClothDetailEntity> clothDetailEntities = clothDetailRepository.findByOrderByClothDetailSequenceDesc();

            return GetClothDetailListResponseDto.success(clothDetailEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }
}
