package com.project.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.back.dto.response.cloth.GetClothStockListResponseDto;
import com.project.back.service.ClothStockService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/shop/cloth-stock")
@RequiredArgsConstructor
public class ClothStockController {
    
    private final ClothStockService clothStockService;

    // 관리자페이지 - 상품 재고

    // 상품관리 상세 재고 보기
    @GetMapping("/cloth/stock/{clothId}")
    public ResponseEntity<? super GetClothStockListResponseDto> getClothStockList (
        @AuthenticationPrincipal String userId,
        @PathVariable("clothId") String clothId
    ) {

        ResponseEntity<? super GetClothStockListResponseDto> response = clothStockService.getClothStockList(userId, clothId);

        return response;
    }
}
