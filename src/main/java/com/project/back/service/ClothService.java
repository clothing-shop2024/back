package com.project.back.service;

import org.springframework.http.ResponseEntity;

import com.project.back.dto.response.cloth.GetClothListResponseDto;
import com.project.back.dto.response.cloth.GetClothResponseDto;

public interface ClothService {
    
    // 옷 전체 리스트 보기
    ResponseEntity<? super GetClothListResponseDto> getClothList();

    // 옷 리스트 검색하기
    ResponseEntity<? super GetClothListResponseDto> getClothSearchList(String searchWord);

    // 옷 카테고리1 리스트 보기
    ResponseEntity<? super GetClothListResponseDto> getCategory1List(String category1);

    // 옷 상세 카테고리2 리스트 보기
    ResponseEntity<? super GetClothListResponseDto> getCategory2List(String category2);

    // 옷 상세 조회순으로 리스트 보기
    ResponseEntity<? super GetClothListResponseDto> getClothViewCountList();

    // 옷 상세 조회순으로 카테고리1 필터 리스트 보기
    ResponseEntity<? super GetClothListResponseDto> getBestCategory1List(String category1);

    // 옷 상세 가격 낮은순으로 카테고리1 필터 리스트 보기
    ResponseEntity<? super GetClothListResponseDto> getClothPriceAscList(String category1);

    // 옷 상세 가격 높은순으로 카테고리1 필터 리스트 보기
    ResponseEntity<? super GetClothListResponseDto> getClothPriceDescList(String category1);

    // 옷 상세 가격 낮은순으로 카테고리2 리스트 보기
    ResponseEntity<? super GetClothListResponseDto> getCategory2PriceAscList(String category2);

    // 옷 상세 가격 낮은순으로 카테고리1 필터 리스트 보기
    ResponseEntity<? super GetClothListResponseDto> getCategory2PriceDescList(String category2);

    // 옷 상세 테이블의 상세페이지 보기
    // ResponseEntity <? super GetClothResponseDto> getCloth(String clothDetailNumber);

}
