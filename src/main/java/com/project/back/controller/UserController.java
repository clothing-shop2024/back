package com.project.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.back.dto.request.user.DeleteUserRequestDto;
import com.project.back.dto.request.user.PatchUserInfoRequestDto;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.user.GetMyInfoResponseDto;
import com.project.back.dto.response.user.GetSignInUserResponseDto;
import com.project.back.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shop/user")
public class UserController {

    private final UserService userService;

    // 사용자 정보 가져오기
    @GetMapping("/")
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(
            @AuthenticationPrincipal String userId) {
        ResponseEntity<? super GetSignInUserResponseDto> response = userService.getSignInUser(userId);
        return response;
    }

    // 사용자의 세부 정보 가져오기
    @GetMapping("/info")
    public ResponseEntity<? super GetMyInfoResponseDto> getMyInfo(
            @AuthenticationPrincipal String userId) {
        ResponseEntity<? super GetMyInfoResponseDto> response = userService.getMyInfo(userId);
        return response;
    }

    // 회원정보 수정하기
    @PatchMapping("info-update/{userId}")
    public ResponseEntity<ResponseDto> patchMyInfo(
            @RequestBody @Valid PatchUserInfoRequestDto requestBody,
            @PathVariable("userId") String userId) {
        ResponseEntity<ResponseDto> response = userService.patchUserInfo(requestBody, userId);
        return response;
    }

    // 회원 탈퇴하기
    @DeleteMapping("/info-delete/{userId}")
    public ResponseEntity<ResponseDto> deleteUser(
            @RequestBody @Valid DeleteUserRequestDto requestBody,
            @PathVariable("userId") String userId) {
        ResponseEntity<ResponseDto> response = userService.deleteUserInfo(requestBody, userId);
        return response;
    }
}