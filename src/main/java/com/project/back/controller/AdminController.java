package com.project.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.back.dto.response.user.GetAdminUserListResponseDto;
import com.project.back.service.AdminService;

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
}
