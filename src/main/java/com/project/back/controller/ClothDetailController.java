package com.project.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.back.dto.response.clothDetail.GetClothDetailListResponseDto;
import com.project.back.service.ClothDetailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/shop/cloth-detail")
@RequiredArgsConstructor
public class ClothDetailController {
    
    private final ClothDetailService clothDetailService;

    // 옷 상세 테이블 전체 리스트 불러오기
    @GetMapping("/list")
    public ResponseEntity<? super GetClothDetailListResponseDto> getClothDetailList () {

        ResponseEntity<? super GetClothDetailListResponseDto> response = clothDetailService.getClothDetailList();

        return response;
    }
}
