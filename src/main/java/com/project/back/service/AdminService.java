package com.project.back.service;

import org.springframework.http.ResponseEntity;

import com.project.back.dto.response.user.GetAdminUserListResponseDto;

public interface AdminService {
    
    // 회원 관리 전체 리스트 최신순 보기
    ResponseEntity<? super GetAdminUserListResponseDto> getUserDescList(String userId);
    // 회원 관리 전체 리스트 오래된순 보기
    ResponseEntity<? super GetAdminUserListResponseDto> getUserAscList(String userId);
    // 회원 관리 아이디로 검색하기
    ResponseEntity<? super GetAdminUserListResponseDto> getUserIdSearchList(String userId, String searchWord);
    // 회원 관리 이름으로 검색하기
    ResponseEntity<? super GetAdminUserListResponseDto> getUserNameSearchList(String userId, String searchWord);
    // 회원 관리 등급으로 검색하기
    ResponseEntity<? super GetAdminUserListResponseDto> getUserGradeSearchList(String userId, String searchWord);
}
