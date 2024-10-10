package com.project.back.service;

import org.springframework.http.ResponseEntity;
import com.project.back.dto.response.clothDetail.GetClothDetailListResponseDto;
import com.project.back.dto.response.clothDetail.GetClothDetailResponseDto;

public interface ClothDetailService {
    
    // 옷 상세 전체 리스트 보기
    ResponseEntity<? super GetClothDetailListResponseDto> getClothDetailList();

    // 옷 상세 리스트 검색하기
    // ResponseEntity<? super GetClothDetailListResponseDto> getClothDetailSearchList(String searchWord);

    // 옷 상세 카테고리1 리스트 보기
    // ResponseEntity<? super GetClothDetailListResponseDto> getClothDetailCategory1List(String clothCategory1);

    // 옷 상세 카테고리2 리스트 보기
    // ResponseEntity<? super GetClothDetailListResponseDto> getClothDetailCategory2List(String clothCategory2);

    // 옷 카테고리1 리스트에서 검색하기
    // ResponseEntity<? super GetClothDetailListResponseDto> getClothDetailCategory1SearchList(String clothCategory1, String searchWord);

    // 옷 카테고리2 리스트에서 검색하기
    // ResponseEntity<? super GetClothDetailListResponseDto> getClothDetailCategory2SearchList(String clothCategory2, String searchWord);

    // 옷 상세 테이블의 상세페이지 보기
    // ResponseEntity <? super GetClothDetailResponseDto> getClothDetail(String clothDetailNumber);

}
