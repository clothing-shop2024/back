package com.project.back.service;

import org.springframework.http.ResponseEntity;

import com.project.back.dto.request.user.DeleteUserRequestDto;
import com.project.back.dto.request.user.PatchUserGradeRequestDto;
import com.project.back.dto.request.user.PatchUserPointsRequestDto;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.user.GetAdminUserListResponseDto;
import com.project.back.dto.response.user.GetMyInfoResponseDto;

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

    // 회원관리 상세보기
    ResponseEntity<? super GetMyInfoResponseDto> getUserDetail(String nickname);

    // 회원관리 사용자 등급 업데이트 하기
    ResponseEntity<ResponseDto> patchUserGrade(PatchUserGradeRequestDto dto, String nickname);

    // 회원관리 사용자 포인트 업데이트 하기
    ResponseEntity<ResponseDto> patchUserPoints(PatchUserPointsRequestDto dto, String nickname);

    // 회원관리 사용자 삭제하기
    ResponseEntity<ResponseDto> deleteUser(String nickname);

}
