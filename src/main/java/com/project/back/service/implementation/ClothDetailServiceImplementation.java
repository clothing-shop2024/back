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

    // 옷 상세 카테고리1 필터 리스트 보기
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

    // 옷 상세 카테고리2 필터 리스트 보기
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

    // 옷 상세 조회순으로 보기 (best)
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

    // 옷 상세 조회순으로 카테고리1 필터 리스트 보기
    @Override
    public ResponseEntity<? super GetClothDetailListResponseDto> getBestClothDetailCategory1List(
        String clothCategory1) {
        
        try {

            List<ClothDetailEntity> clothDetailEntities = clothDetailRepository.findByClothCategory1ContainsOrderByViewCountDesc(clothCategory1);

            return GetClothDetailListResponseDto.success(clothDetailEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    // 옷 상세 카테고리1 가격 낮은순 리스트 보기
    @Override
    public ResponseEntity<? super GetClothDetailListResponseDto> getClothDetailPriceAscList(
        String clothCategory1
    ) {
        
        try {

            List<ClothDetailEntity> clothDetailEntities = clothDetailRepository.findByClothCategory1ContainsOrderByPriceAsc(clothCategory1);

            return GetClothDetailListResponseDto.success(clothDetailEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    // 옷 상세 카테고리1 가격 높은순 리스트 보기
    @Override
    public ResponseEntity<? super GetClothDetailListResponseDto> getClothDetailPriceDescList(
        String clothCategory1
    ) {
        
        try {

            List<ClothDetailEntity> clothDetailEntities = clothDetailRepository.findByClothCategory1ContainsOrderByPriceDesc(clothCategory1);

            return GetClothDetailListResponseDto.success(clothDetailEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    // 옷 상세 카테고리1 가격 낮은순 리스트 보기
    @Override
    public ResponseEntity<? super GetClothDetailListResponseDto> getClothDetailCategory2PriceAscList(
        String clothCategory2
    ) {
        
        try {

            List<ClothDetailEntity> clothDetailEntities = clothDetailRepository.findByClothCategory2ContainsOrderByPriceAsc(clothCategory2);

            return GetClothDetailListResponseDto.success(clothDetailEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    // 옷 상세 카테고리1 가격 높은순 리스트 보기
    @Override
    public ResponseEntity<? super GetClothDetailListResponseDto> getClothDetailCategory2PriceDescList(
        String clothCategory2
    ) {
        
        try {

            List<ClothDetailEntity> clothDetailEntities = clothDetailRepository.findByClothCategory2ContainsOrderByPriceDesc(clothCategory2);

            return GetClothDetailListResponseDto.success(clothDetailEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }
}
