package com.project.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.back.dto.request.cloth.PostClothInfoRequestDto;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.cloth.GetClothInfoResponseDto;
import com.project.back.service.ClothInfoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/shop/cloth-info")
@RequiredArgsConstructor
public class clothInfoController {

    private final ClothInfoService clothInfoService;

    @GetMapping("/{clothNumber}")
    public ResponseEntity<? super GetClothInfoResponseDto> getClothInfo(
            @PathVariable("clothNumber") String clothNumber) {
        ResponseEntity<? super GetClothInfoResponseDto> response = clothInfoService.getClothInfo(clothNumber);
        return response;
    }

    @PostMapping("/order")
    public ResponseEntity<ResponseDto> postClothInfo(
            @RequestBody @Valid PostClothInfoRequestDto requestBody,
            @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = clothInfoService.postClothInfo(requestBody, userId);
        return response;
    };

}
