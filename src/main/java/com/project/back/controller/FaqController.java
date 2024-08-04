package com.project.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.back.dto.request.faq.PostFaqRequestDto;
import com.project.back.dto.request.faq.PutFaqRequestDto;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.faq.GetFaqListResponseDto;
import com.project.back.service.FaqService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shop/faq")
public class FaqController {

    private final FaqService faqService;

    // 자주하는 질문 전체 리스트 불러오기
    @GetMapping("/list")
    public ResponseEntity<? super GetFaqListResponseDto> getFaqList () {
        ResponseEntity<? super GetFaqListResponseDto> response = faqService.getFaqList();
        return response;
    }

    // 자주하는 질문 작성하기
    @PostMapping("/regist")
    ResponseEntity<ResponseDto> postFaq (
        @RequestBody @Valid PostFaqRequestDto requestBody, @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = faqService.postFaq(requestBody, userId);
        return response;
    }

    // 자주하는 질문 수정하기
    @PutMapping("/{faqNumber}/modify")
    public ResponseEntity<ResponseDto> putFaq (
        @RequestBody @Valid PutFaqRequestDto requestDto,
        @PathVariable("faqNumber") int faqNumber,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = faqService.putFaq(requestDto, faqNumber, userId);
        return response;
    }

    // 자주하는 질문 삭제하기
    @DeleteMapping("/{faqNumber}/delete")
    public ResponseEntity<ResponseDto> deleteFaq (
        @PathVariable("faqNumber") int faqNumber,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = faqService.deleteFaq(faqNumber, userId);
        return response;
    }

}