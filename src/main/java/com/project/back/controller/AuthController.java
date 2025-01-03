
package com.project.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.back.dto.request.auth.EmailAuthCheckRequestDto;
import com.project.back.dto.request.auth.EmailAuthRequestDto;
import com.project.back.dto.request.auth.FindIdEmailAuthRequestDto;
import com.project.back.dto.request.auth.FindIdRequestDto;
import com.project.back.dto.request.auth.FindPasswordRequestDto;
import com.project.back.dto.request.auth.FindPasswordResetRequestDto;
import com.project.back.dto.request.auth.IdCheckRequestDto;
import com.project.back.dto.request.auth.NicknameCheckRequestDto;
import com.project.back.dto.request.auth.SignInRequestDto;
import com.project.back.dto.request.auth.SignUpRequestDto;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.auth.FindIdResponseDto;
import com.project.back.dto.response.auth.SignInResponseDto;
import com.project.back.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shop/auth")
public class AuthController {

    private final AuthService authService;

    // 로그인
    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> signIn(
            @RequestBody @Valid SignInRequestDto requestBody) {
        ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    }

    // 아이디 중복 확인
    @PostMapping("/id-check")
    public ResponseEntity<ResponseDto> idCheck(
            @RequestBody @Valid IdCheckRequestDto requestBody) {
        ResponseEntity<ResponseDto> response = authService.idCheck(requestBody);
        return response;
    }

    // 닉네임 중복 확인
    @PostMapping("/nickname-check")
    public ResponseEntity<ResponseDto> nicknameCheck(
            @RequestBody @Valid NicknameCheckRequestDto requestBody) {
        ResponseEntity<ResponseDto> response = authService.nicknameCheck(requestBody);
        return response;
    }

    // 이메일 인증
    @PostMapping("/email-auth")
    public ResponseEntity<ResponseDto> emailAuth(
            @RequestBody @Valid EmailAuthRequestDto requestBody) {
        ResponseEntity<ResponseDto> response = authService.emailAuth(requestBody);
        return response;
    }

    // 이메일 인증 확인
    @PostMapping("/email-auth-check")
    public ResponseEntity<ResponseDto> emailAuthCheck(
            @RequestBody @Valid EmailAuthCheckRequestDto requestBody) {
        ResponseEntity<ResponseDto> response = authService.emailAuthCheck(requestBody);
        return response;
    }

    // 회원가입
    @PostMapping("/sign-up")
    public ResponseEntity<ResponseDto> signUp(
            @RequestBody @Valid SignUpRequestDto requestBody) {
        ResponseEntity<ResponseDto> response = authService.signUp(requestBody);
        return response;
    }

    // 아이디 찾기의 이메일 인증
    @PostMapping("/find-id-email-auth")
    public ResponseEntity<ResponseDto> findIdEmailAuth(
            @RequestBody @Valid FindIdEmailAuthRequestDto requestBody) {
        ResponseEntity<ResponseDto> response = authService.findIdEmailAuth(requestBody);
        return response;
    }

    // 아이디 찾기
    @PostMapping("/find-id")
    public ResponseEntity<? super FindIdResponseDto> findId(
            @RequestBody @Valid FindIdRequestDto requestBody) {
        ResponseEntity<? super FindIdResponseDto> response = authService.findId(requestBody);
        return response;
    }

    // 비밀번호 찾기의 이메일 인증
    @PostMapping("/find-password")
    public ResponseEntity<ResponseDto> findPasswordEmailAuth(
            @RequestBody @Valid FindPasswordRequestDto requestBody) {
        ResponseEntity<ResponseDto> response = authService.findPassword(requestBody);
        return response;
    }

    // 비밀번호 재설정
    @PutMapping("/find-password-reset")
    public ResponseEntity<ResponseDto> findPasswordReset(
            @RequestBody @Valid FindPasswordResetRequestDto requestBody) {
        ResponseEntity<ResponseDto> response = authService.findPasswordReset(requestBody);
        return response;
    }
}