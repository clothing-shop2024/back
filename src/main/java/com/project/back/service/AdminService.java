package com.project.back.service;

import org.springframework.http.ResponseEntity;

import com.project.back.dto.response.user.GetAdminUserListResponseDto;

public interface AdminService {
    
    // 회원 관리 전체 리스트 보기
    ResponseEntity<? super GetAdminUserListResponseDto> getUserList(String userId);
}
