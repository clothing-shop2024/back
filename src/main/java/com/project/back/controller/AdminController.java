package com.project.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.back.dto.request.user.PatchUserGradeRequestDto;
import com.project.back.dto.request.user.PatchUserPointsRequestDto;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.cloth.GetAdminClothListResponseDto;
import com.project.back.dto.response.cloth.GetClothResponseDto;
import com.project.back.dto.response.cloth.GetClothStockListResponseDto;
import com.project.back.dto.response.user.GetAdminUserListResponseDto;
import com.project.back.dto.response.user.GetMyInfoResponseDto;
import com.project.back.service.AdminService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/shop/admin")
@RequiredArgsConstructor
public class AdminController {
    
    private final AdminService adminService;

    // 회원관리 전체 리스트 최신순 불러오기
    @GetMapping("/user/list/desc")
    public ResponseEntity<? super GetAdminUserListResponseDto> getUserDescList (
        @AuthenticationPrincipal String userId
    ) {

        ResponseEntity<? super GetAdminUserListResponseDto> response = adminService.getUserDescList(userId);

        return response;
    }

    // 회원관리 전체 리스트 가입 오래된 순으로 불러오기
    @GetMapping("/user/list/asc")
    public ResponseEntity<? super GetAdminUserListResponseDto> getUserAscList (
        @AuthenticationPrincipal String userId
    ) {

        ResponseEntity<? super GetAdminUserListResponseDto> response = adminService.getUserAscList(userId);

        return response;
    }

    // 회원관리 아이디로 검색하기
    @GetMapping("/user/list/userId/search")
    public ResponseEntity<? super GetAdminUserListResponseDto> getUserIdSearchList (
        @AuthenticationPrincipal String userId,
        @RequestParam("word") String word
    ) {
        ResponseEntity<? super GetAdminUserListResponseDto> response = adminService.getUserIdSearchList(userId, word);

        return response;
    }

    // 회원관리 이름으로 검색하기
    @GetMapping("/user/list/userName/search")
    public ResponseEntity<? super GetAdminUserListResponseDto> getUserNameSearchList (
        @AuthenticationPrincipal String userId,
        @RequestParam("word") String word
    ) {
        ResponseEntity<? super GetAdminUserListResponseDto> response = adminService.getUserNameSearchList(userId, word);

        return response;
    }

    // 회원관리 등급으로 검색하기
    @GetMapping("/user/list/grade/search")
    public ResponseEntity<? super GetAdminUserListResponseDto> getUserGradeSearchList (
        @AuthenticationPrincipal String userId,
        @RequestParam("word") String word
    ) {
        ResponseEntity<? super GetAdminUserListResponseDto> response = adminService.getUserGradeSearchList(userId, word);

        return response;
    }

    // 사용자의 세부 정보 가져오기
    @GetMapping("/user/list/{nickname}")
    public ResponseEntity<? super GetMyInfoResponseDto> getUserDetail (
        @AuthenticationPrincipal String userId,
        @PathVariable("nickname") String nickname
        ) {
        ResponseEntity<? super GetMyInfoResponseDto> response = adminService.getUserDetail(nickname);

        return response;
    }

    // 상품관리 전체 보기
    @GetMapping("/cloth/list")
    public ResponseEntity<? super GetAdminClothListResponseDto> getAdminClothList (
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super GetAdminClothListResponseDto> response = adminService.getAdminClothList(userId);
        
        return response;
    }

    // 상품관리 상세 보기
    @GetMapping("/cloth/{clothId}")
    public ResponseEntity<? super GetClothResponseDto> getAdminClothDetail (
        @AuthenticationPrincipal String userId,
        @PathVariable("clothId") String clothId
    ) {
        
        ResponseEntity<? super GetClothResponseDto> response = adminService.getAdminClothDetail(userId, clothId);

        return response;
    }

    // 상품관리 상세 재고 보기
    @GetMapping("/cloth/stock/{clothId}")
    public ResponseEntity<? super GetClothStockListResponseDto> getClothStockList (
        @AuthenticationPrincipal String userId,
        @PathVariable("clothId") String clothId
    ) {

        ResponseEntity<? super GetClothStockListResponseDto> response = adminService.getClothStockList(userId, clothId);

        return response;
    }

    // 사용자의 등급 수정하기
    @PatchMapping("/user/list/grade/{nickname}")
    public ResponseEntity<ResponseDto> patchUserGrade (
        @RequestBody @Valid PatchUserGradeRequestDto requestBody,
        @AuthenticationPrincipal String userId,
        @PathVariable("nickname") String nickname
    ) {
        ResponseEntity<ResponseDto> response = adminService.patchUserGrade(requestBody, nickname);
        return response;
    }

    // 사용자의 포인트 수정하기
    @PatchMapping("/user/list/points/{nickname}")
    public ResponseEntity<ResponseDto> patchUserPoints (
        @RequestBody @Valid PatchUserPointsRequestDto requestBody,
        @AuthenticationPrincipal String userId,
        @PathVariable("nickname") String nickname
    ) {
        ResponseEntity<ResponseDto> response = adminService.patchUserPoints (requestBody, nickname);
        return response;
    }

    // 회원 삭제하기
    @DeleteMapping("/user/list/delete/{nickname}")
    public ResponseEntity<ResponseDto> deleteUser (
        @AuthenticationPrincipal String userId,
        @PathVariable("nickname") String nickname
    ) {
        ResponseEntity<ResponseDto> response = adminService.deleteUser(nickname);
        
        return response;
    }

}
