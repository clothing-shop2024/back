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
    
    // 옷 상세 테이블 전체 리스트 보기
    @Override
    public ResponseEntity<? super GetClothDetailListResponseDto> getClothDetailList() {
        
        try {

            List<ClothDetailEntity> clothDetailEntities = clothDetailRepository.findByOrderByClothDetailSequenceDesc();

            return GetClothDetailListResponseDto.success(clothDetailEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    // 옷 상세 전체 검색 리스트 보기
    @Override
    public ResponseEntity<? super GetClothDetailListResponseDto> getClothDetailSearchList(String searchWord) {
        
        try {

            List<ClothDetailEntity> clothDetailEntities = clothDetailRepository.findByClothDetailNameContainsOrderByClothDetailSequenceDesc(searchWord);

            return GetClothDetailListResponseDto.success(clothDetailEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }


    @Override
    public ResponseEntity<? super GetClothDetailListResponseDto> getClothDetailCategory1List(String clothCategory1) {
        
        try {

            List<ClothDetailEntity> clothDetailEntities = clothDetailRepository.findByClothCategory1ContainsOrderByClothDetailSequenceDesc(clothCategory1);

            return GetClothDetailListResponseDto.success(clothDetailEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }


    @Override
    public ResponseEntity<? super GetClothDetailListResponseDto> getClothDetailCategory2List(String clothCategory2) {
        
        try {

            List<ClothDetailEntity> clothDetailEntities = clothDetailRepository.findByClothCategory2ContainsOrderByClothDetailSequenceDesc(clothCategory2);

            return GetClothDetailListResponseDto.success(clothDetailEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }


    @Override
    public ResponseEntity<? super GetClothDetailListResponseDto> getClothDetailCategory1SearchList(
            String clothCategory1, String searchWord) {
        
        try {

            List<ClothDetailEntity> clothDetailEntities = clothDetailRepository.findByClothCategory1AndClothDetailNameContainsOrderByClothDetailSequenceDesc(clothCategory1, searchWord);

            return GetClothDetailListResponseDto.success(clothDetailEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }


    @Override
    public ResponseEntity<? super GetClothDetailListResponseDto> getClothDetailCategory2SearchList(
            String clothCategory2, String searchWord) {
        
        try {

            List<ClothDetailEntity> clothDetailEntities = clothDetailRepository.findByClothCategory2AndClothDetailNameContainsOrderByClothDetailSequenceDesc(clothCategory2, searchWord);

            return GetClothDetailListResponseDto.success(clothDetailEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetClothDetailListResponseDto> getClothDetailViewCountList() {
        
        try {

            List<ClothDetailEntity> clothDetailEntities = clothDetailRepository.findByOrderByViewCountDesc();

            return GetClothDetailListResponseDto.success(clothDetailEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }
    
}
