package com.project.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.back.dto.request.user.DeleteUserRequestDto;
import com.project.back.dto.request.user.PatchUserGradeRequestDto;
import com.project.back.dto.request.user.PatchUserInfoRequestDto;
import com.project.back.dto.request.user.PostUserPointRequestDto;
import com.project.back.dto.request.user.PutEmailModifyRequestDto;
import com.project.back.dto.request.user.PutPasswordModifyRequestDto;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.user.GetMyInfoResponseDto;
import com.project.back.dto.response.user.GetMyQnaListResponseDto;
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

    // 나의 문의사항 전체 게시물 리스트 불러오기
    @GetMapping("/qna/list")
    public ResponseEntity<? super GetMyQnaListResponseDto> getMyQnaList (
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super GetMyQnaListResponseDto> response = userService.getMyQnaList(userId);

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

    // 비밀번호 수정하기
    @PutMapping("/info/password-modify")
    public ResponseEntity<ResponseDto> putPasswordModify(
            @RequestBody @Valid PutPasswordModifyRequestDto requestBody,
            @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = userService.putPasswordModify(requestBody, userId);
        return response;
    }

    // 이메일 수정하기
    @PutMapping("/info/email-modify")
    public ResponseEntity<ResponseDto> putEmailModify(
            @RequestBody @Valid PutEmailModifyRequestDto requestBody,
            @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = userService.putEmailModify(requestBody, userId);
        return response;
    }

    // 회원 탈퇴하기
    @PostMapping("/info-delete/{userId}")
    public ResponseEntity<ResponseDto> deleteUser(
            @RequestBody @Valid DeleteUserRequestDto requestBody,
            @PathVariable("userId") String userId) {
        ResponseEntity<ResponseDto> response = userService.deleteUserInfo(requestBody, userId);
        return response;
    }

    // 사용자 등급 업데이트하기
    @PatchMapping("/update-grade/{userId}")
    public ResponseEntity<ResponseDto> updateUserGrade(
            @RequestBody @Valid PatchUserGradeRequestDto requestBody,
            @PathVariable("userId") String userId) {
        ResponseEntity<ResponseDto> response = userService.updateUserGrade(requestBody, userId);
        return response;
    }

    // 사용자 포인트 추가하기
    @PostMapping("/add-points/{userId}")
    public ResponseEntity<ResponseDto> addUserPoints(
            @RequestBody @Valid PostUserPointRequestDto requestBody,
            @PathVariable("userId") String userId) {
        return userService.addUserPoints(requestBody, userId);
    }

    // 사용자 포인트 설정하기
    @PostMapping("/set-points/{userId}")
    public ResponseEntity<ResponseDto> setUserPoints(
            @RequestBody @Valid PostUserPointRequestDto requestBody,
            @PathVariable("userId") String userId) {
        return userService.setUserPoints(requestBody, userId);
    }
}