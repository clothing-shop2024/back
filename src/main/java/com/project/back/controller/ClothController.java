package com.project.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.cloth.favorite.GetFavoriteCheckResponseDto;
import com.project.back.dto.response.cloth.favorite.GetFavoriteClothListResponseDto;
import com.project.back.service.ClothService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shop/cloth")
public class ClothController {

    private final ClothService clothService;

    @PostMapping("/favorite/{clothNumber}")
    public ResponseEntity<ResponseDto> postFavorite(
            @PathVariable("clothNumber") int clothNumber,
            @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = clothService.postFavorite(userId, clothNumber);
        return response;
    }

    @GetMapping("/favorite/{clothNumber}")
    public ResponseEntity<? super GetFavoriteCheckResponseDto> getFavoriteCheck(
            @PathVariable("clothNumber") int clothNumber,
            @AuthenticationPrincipal String userId) {
        ResponseEntity<? super GetFavoriteCheckResponseDto> response = clothService.getFavoriteCheck(userId,
                clothNumber);
        return response;
    }

    @GetMapping("/favorite/list")
    public ResponseEntity<? super GetFavoriteClothListResponseDto> getFavoriteList(
            @AuthenticationPrincipal String userId) {
        ResponseEntity<? super GetFavoriteClothListResponseDto> response = clothService
                .getFavoriteList(userId);
        return response;
    }

    @DeleteMapping("/favorite/{clothNumber}")
    public ResponseEntity<ResponseDto> deleteFavorite(
            @PathVariable("clothNumber") int clothNumber,
            @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = clothService.deleteFavorite(userId, clothNumber);
        return response;
    }
}
