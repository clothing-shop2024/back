package com.project.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.back.dto.response.cloth.GetAdminClothListResponseDto;
import com.project.back.dto.response.cloth.GetClothListResponseDto;
import com.project.back.dto.response.cloth.GetClothResponseDto;
import com.project.back.service.ClothService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/shop/cloth")
@RequiredArgsConstructor
public class ClothController {
    
    private final ClothService clothService;

    // 옷 상세 테이블 전체 리스트 불러오기
    @GetMapping("/list")
    public ResponseEntity<? super GetClothListResponseDto> getClothList () {

        ResponseEntity<? super GetClothListResponseDto> response = clothService.getClothList();

        return response;
    }

    // 옷 상세 전체 검색 리스트 불러오기
    @GetMapping("/list/search")
    public ResponseEntity<? super GetClothListResponseDto> getClothSearchList (
        @RequestParam("word") String word
    ) {

        ResponseEntity<? super GetClothListResponseDto> response = clothService.getClothSearchList(word);

        return response;

    }

    // 옷 상세 카테고리1 리스트 보기
    @GetMapping("/list/category1/{category1}")
    public ResponseEntity<? super GetClothListResponseDto> getCategory1List (
        @PathVariable("category1") String category1
    ) {
        
        ResponseEntity<? super GetClothListResponseDto> response = clothService.getCategory1List(category1);

        return response;

    }

    // 옷 상세 카테고리2 리스트 보기
    @GetMapping("/list/category2/{category2}")
    public ResponseEntity<? super GetClothListResponseDto> getCategory2List (
        @PathVariable("category2") String category2
    ) {
        
        ResponseEntity<? super GetClothListResponseDto> response = clothService.getCategory2List(category2);

        return response;
        
    }

    // 옷 상세 조회순으로 리스트 보기
    @GetMapping("/list/best")
    public ResponseEntity<? super GetClothListResponseDto> getClothViewCountList () {

        ResponseEntity<? super GetClothListResponseDto> response = clothService.getClothViewCountList();

        return response;
    }

    // 조회순으로 옷 상세 카테고리1 리스트 보기
    @GetMapping("/list/category1/{category1}/best")
    public ResponseEntity<? super GetClothListResponseDto> getBestCategory1List(
        @PathVariable("category1") String category1
    ) {

        ResponseEntity<? super GetClothListResponseDto> response = clothService.getBestCategory1List(category1);

        return response;
    }

    // 옷 상세 가격 낮은순으로 카테고리1 리스트 보기
    @GetMapping("/list/category1/{category1}/price-asc")
    public ResponseEntity<? super GetClothListResponseDto> getPriceAscCategory1List (
        @PathVariable("category1") String category1
    ) {

        ResponseEntity<? super GetClothListResponseDto> response = clothService.getClothPriceAscList(category1);

        return response;
    }

    // 옷 상세 가격 높은순으로 카테고리1 리스트 보기
    @GetMapping("/list/category1/{category1}/price-desc")
    public ResponseEntity<? super GetClothListResponseDto> getPriceDescCategory1List (
        @PathVariable("category1") String category1
    ) {

        ResponseEntity<? super GetClothListResponseDto> response = clothService.getClothPriceDescList(category1);

        return response;
    }

    // 옷 상세 가격 낮은순으로 카테고리2 리스트 보기
    @GetMapping("/list/category2/{category2}/price-asc")
    public ResponseEntity<? super GetClothListResponseDto> getPriceAscCategory2List (
        @PathVariable("category2") String category2
    ) {

        ResponseEntity<? super GetClothListResponseDto> response = clothService.getCategory2PriceAscList(category2);

        return response;
    }

    // 옷 상세 가격 높은순으로 카테고리2 리스트 보기
    @GetMapping("/list/category2/{category2}/price-desc")
    public ResponseEntity<? super GetClothListResponseDto> getPriceDescCategory2List (
        @PathVariable("category2") String category2
    ) {

        ResponseEntity<? super GetClothListResponseDto> response = clothService.getCategory2PriceDescList(category2);

        return response;
    }

    // 관리자페이지 - 상품관리
    // 상품관리 전체 보기
    @GetMapping("/admin/list")
    public ResponseEntity<? super GetAdminClothListResponseDto> getAdminClothList (
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super GetAdminClothListResponseDto> response = clothService.getAdminClothList(userId);
        
        return response;
    }

    // 상품관리 상세 보기
    @GetMapping("/admin/{clothId}")
    public ResponseEntity<? super GetClothResponseDto> getAdminClothDetail (
        @AuthenticationPrincipal String userId,
        @PathVariable("clothId") String clothId
    ) {
        
        ResponseEntity<? super GetClothResponseDto> response = clothService.getAdminClothDetail(userId, clothId);

        return response;
    }

}
