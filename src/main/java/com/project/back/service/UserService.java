package com.project.back.service;

import org.springframework.http.ResponseEntity;

import com.project.back.dto.request.auth.EmailAuthRequestDto;
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

public interface UserService {

    // 사용자 정보 가져오기
    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String userId);

    // 사용자의 세부 정보 가져오기
    ResponseEntity<? super GetMyInfoResponseDto> getMyInfo(String userId);

    // 나의 문의내역 리스트 보기
    ResponseEntity<? super GetMyQnaListResponseDto> getMyQnaList(String userId);

    // 회원정보 수정하기
    ResponseEntity<ResponseDto> patchUserInfo(PatchUserInfoRequestDto dto, String userId);

    // 비밀번호 수정하기
    ResponseEntity<ResponseDto> putPasswordModify(PutPasswordModifyRequestDto dto, String userId);

    // 이메일 인증하기
    ResponseEntity<ResponseDto> emailAuth(EmailAuthRequestDto dto);

    // 이메일 수정하기
    ResponseEntity<ResponseDto> putEmailModify(PutEmailModifyRequestDto dto, String userId);

    // 회원 탈퇴하기
    ResponseEntity<ResponseDto> deleteUserInfo(DeleteUserRequestDto dto, String userId);

    // 사용자 등급 업데이트하기
    ResponseEntity<ResponseDto> updateUserGrade(PatchUserGradeRequestDto dto, String userId);

    // 사용자 포인트 추가하기
    ResponseEntity<ResponseDto> addUserPoints(PostUserPointRequestDto dto, String userId);

    // 사용자 포인트 설정하기
    ResponseEntity<ResponseDto> setUserPoints(PostUserPointRequestDto dto, String userId);
}
