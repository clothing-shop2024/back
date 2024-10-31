package com.project.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    // 옷 상세 전체 검색 리스트 불러오기
    @GetMapping("/list/search")
    public ResponseEntity<? super GetClothDetailListResponseDto> getClothDetailSearchList (
        @RequestParam("word") String word
    ) {

        ResponseEntity<? super GetClothDetailListResponseDto> response = clothDetailService.getClothDetailSearchList(word);

        return response;

    }

    // 옷 상세 카테고리1 리스트 보기
    @GetMapping("/list/category1/{clothCategory1}")
    public ResponseEntity<? super GetClothDetailListResponseDto> getClothDetailCategory1List (
        @PathVariable("clothCategory1") String clothCategory1
    ) {
        
        ResponseEntity<? super GetClothDetailListResponseDto> response = clothDetailService.getClothDetailCategory1List(clothCategory1);

        return response;

    }

    // 옷 상세 카테고리2 리스트 보기
    @GetMapping("/list/category2/{clothCategory2}")
    public ResponseEntity<? super GetClothDetailListResponseDto> getClothDetailCategory2List (
        @PathVariable("clothCategory2") String clothCategory2
    ) {
        
        ResponseEntity<? super GetClothDetailListResponseDto> response = clothDetailService.getClothDetailCategory2List(clothCategory2);

        return response;
        
    }

    // 옷 상세 조회순으로 리스트 보기
    @GetMapping("/list/best")
    public ResponseEntity<? super GetClothDetailListResponseDto> getClothDetailViewCountList () {

        ResponseEntity<? super GetClothDetailListResponseDto> response = clothDetailService.getClothDetailViewCountList();

        return response;
    }

    // 조회순으로 옷 상세 카테고리1 리스트 보기
    @GetMapping("/list/category1/{clothCategory1}/best")
    public ResponseEntity<? super GetClothDetailListResponseDto> getBestClothDetailCategory1List(
        @PathVariable("clothCategory1") String clothCategory1
    ) {

        ResponseEntity<? super GetClothDetailListResponseDto> response = clothDetailService.getBestClothDetailCategory1List(clothCategory1);

        return response;
    }

    // 옷 상세 가격 낮은순으로 카테고리1 리스트 보기
    @GetMapping("/list/category1/{clothCategory1}/price-asc")
    public ResponseEntity<? super GetClothDetailListResponseDto> getPriceAscClothDetailCategory1List (
        @PathVariable("clothCategory1") String clothCategory1
    ) {

        ResponseEntity<? super GetClothDetailListResponseDto> response = clothDetailService.getClothDetailPriceAscList(clothCategory1);

        return response;
    }

    // 옷 상세 가격 높은순으로 카테고리1 리스트 보기
    @GetMapping("/list/category1/{clothCategory1}/price-desc")
    public ResponseEntity<? super GetClothDetailListResponseDto> getPriceDescClothDetailCategory1List (
        @PathVariable("clothCategory1") String clothCategory1
    ) {

        ResponseEntity<? super GetClothDetailListResponseDto> response = clothDetailService.getClothDetailPriceDescList(clothCategory1);

        return response;
    }

    // 옷 상세 가격 낮은순으로 카테고리2 리스트 보기
    @GetMapping("/list/category2/{clothCategory2}/price-asc")
    public ResponseEntity<? super GetClothDetailListResponseDto> getPriceAscClothDetailCategory2List (
        @PathVariable("clothCategory2") String clothCategory2
    ) {

        ResponseEntity<? super GetClothDetailListResponseDto> response = clothDetailService.getClothDetailCategory2PriceAscList(clothCategory2);

        return response;
    }

    // 옷 상세 가격 높은순으로 카테고리2 리스트 보기
    @GetMapping("/list/category2/{clothCategory2}/price-desc")
    public ResponseEntity<? super GetClothDetailListResponseDto> getPriceDescClothDetailCategory2List (
        @PathVariable("clothCategory2") String clothCategory2
    ) {

        ResponseEntity<? super GetClothDetailListResponseDto> response = clothDetailService.getClothDetailCategory2PriceDescList(clothCategory2);

        return response;
    }

}
