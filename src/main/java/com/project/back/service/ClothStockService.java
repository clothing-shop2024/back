package com.project.back.service;

import org.springframework.http.ResponseEntity;

import com.project.back.dto.response.cloth.GetClothStockListResponseDto;

public interface ClothStockService {
    
    // 관리자페이지 - 상품관리
    // 상품관리 상세 보기 (clothStock 부분)
    ResponseEntity<? super GetClothStockListResponseDto> getClothStockList(String userId, String clothId);
}
