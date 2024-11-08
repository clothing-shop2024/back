package com.project.back.service;

import org.springframework.http.ResponseEntity;

import com.project.back.dto.response.user.GetAdminUserListResponseDto;

public interface AdminService {
    
    // 회원 관리 전체 리스트 최신순 보기
    ResponseEntity<? super GetAdminUserListResponseDto> getUserDescList(String userId);
    // 회원 관리 전체 리스트 오래된순 보기
    ResponseEntity<? super GetAdminUserListResponseDto> getUserAscList(String userId);
}
