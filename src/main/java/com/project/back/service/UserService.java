package com.project.back.service;

import org.springframework.http.ResponseEntity;

import com.project.back.dto.request.user.DeleteUserRequestDto;
import com.project.back.dto.request.user.PatchUserInfoRequestDto;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.user.GetMyInfoResponseDto;
import com.project.back.dto.response.user.GetSignInUserResponseDto;

public interface UserService {

    // 사용자 정보 가져오기
    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String userId);

    // 사용자의 세부 정보 가져오기
    ResponseEntity<? super GetMyInfoResponseDto> getMyInfo(String userId);

    // 회원정보 수정하기
    ResponseEntity<ResponseDto> patchUserInfo(PatchUserInfoRequestDto dto, String userId);

    // 회원 탈퇴하기
    ResponseEntity<ResponseDto> deleteUserInfo(DeleteUserRequestDto dto, String userId);

}
