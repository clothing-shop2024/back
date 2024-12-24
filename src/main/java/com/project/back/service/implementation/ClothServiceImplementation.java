package com.project.back.service.implementation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.cloth.GetClothListResponseDto;
import com.project.back.entity.ClothEntity;
import com.project.back.repository.ClothRepository;
import com.project.back.service.ClothService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClothServiceImplementation implements ClothService {

    private final ClothRepository clothRepository;
    
    // 옷 테이블 전체 리스트 보기
    @Override
    public ResponseEntity<? super GetClothListResponseDto> getClothList() {
        
        try {

            List<ClothEntity> clothEntities = clothRepository.findByOrderByClothNumberDesc();

            return GetClothListResponseDto.success(clothEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    // 옷 상세 전체 검색 리스트 보기
    @Override
    public ResponseEntity<? super GetClothListResponseDto> getClothSearchList(String searchWord) {
        
        try {

            List<ClothEntity> clothEntities = clothRepository.findByClothNameContainsOrderByClothNumberDesc(searchWord);

            return GetClothListResponseDto.success(clothEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    // 옷 상세 카테고리1 필터 리스트 보기
    @Override
    public ResponseEntity<? super GetClothListResponseDto> getCategory1List(String category1) {
        
        try {

            List<ClothEntity> clothEntities = clothRepository.findByCategory1ContainsOrderByClothNumberDesc(category1);

            return GetClothListResponseDto.success(clothEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    // 옷 상세 카테고리2 필터 리스트 보기
    @Override
    public ResponseEntity<? super GetClothListResponseDto> getCategory2List(String category2) {
        
        try {

            List<ClothEntity> clothEntities = clothRepository.findByCategory2ContainsOrderByClothNumberDesc(category2);

            return GetClothListResponseDto.success(clothEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    // 옷 상세 조회순으로 보기 (best)
    @Override
    public ResponseEntity<? super GetClothListResponseDto> getClothViewCountList() {
        
        try {

            List<ClothEntity> clothEntities = clothRepository.findByOrderByViewCountDesc();

            return GetClothListResponseDto.success(clothEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    // 옷 상세 조회순으로 카테고리1 필터 리스트 보기
    @Override
    public ResponseEntity<? super GetClothListResponseDto> getBestCategory1List(
        String category1) {
        
        try {

            List<ClothEntity> clothEntities = clothRepository.findByCategory1ContainsOrderByViewCountDesc(category1);

            return GetClothListResponseDto.success(clothEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    // 옷 상세 카테고리1 가격 낮은순 리스트 보기
    @Override
    public ResponseEntity<? super GetClothListResponseDto> getClothPriceAscList(
        String category1
    ) {
        
        try {

            List<ClothEntity> clothEntities = clothRepository.findByCategory1ContainsOrderByPriceAsc(category1);

            return GetClothListResponseDto.success(clothEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    // 옷 상세 카테고리1 가격 높은순 리스트 보기
    @Override
    public ResponseEntity<? super GetClothListResponseDto> getClothPriceDescList(
        String category1
    ) {
        
        try {

            List<ClothEntity> clothEntities = clothRepository.findByCategory1ContainsOrderByPriceDesc(category1);

            return GetClothListResponseDto.success(clothEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    // 옷 상세 카테고리1 가격 낮은순 리스트 보기
    @Override
    public ResponseEntity<? super GetClothListResponseDto> getCategory2PriceAscList(
        String category2
    ) {
        
        try {

            List<ClothEntity> clothEntities = clothRepository.findByCategory2ContainsOrderByPriceAsc(category2);

            return GetClothListResponseDto.success(clothEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    // 옷 상세 카테고리1 가격 높은순 리스트 보기
    @Override
    public ResponseEntity<? super GetClothListResponseDto> getCategory2PriceDescList(
        String category2
    ) {
        
        try {

            List<ClothEntity> clothEntities = clothRepository.findByCategory2ContainsOrderByPriceDesc(category2);

            return GetClothListResponseDto.success(clothEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }
}
