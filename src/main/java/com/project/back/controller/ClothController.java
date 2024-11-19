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

    @PostMapping("/favorite/{clothId}")
    public ResponseEntity<ResponseDto> postFavorite(
            @PathVariable("clothId") int clothId,
            @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = clothService.postFavorite(userId, clothId);
        return response;
    }

    @GetMapping("/favorite/{clothId}")
    public ResponseEntity<? super GetFavoriteCheckResponseDto> getFavoriteCheck(
            @PathVariable("clothId") int clothId,
            @AuthenticationPrincipal String userId) {
        ResponseEntity<? super GetFavoriteCheckResponseDto> response = clothService.getFavoriteCheck(userId,
                clothId);
        return response;
    }

    @GetMapping("/favorite/list")
    public ResponseEntity<? super GetFavoriteClothListResponseDto> getFavoriteList(
            @AuthenticationPrincipal String userId) {
        ResponseEntity<? super GetFavoriteClothListResponseDto> response = clothService
                .getFavoriteList(userId);
        return response;
    }

    @DeleteMapping("/favorite/{clothId}")
    public ResponseEntity<ResponseDto> deleteFavorite(
            @PathVariable("clothId") int clothId,
            @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = clothService.deleteFavorite(userId, clothId);
        return response;
    }
}
