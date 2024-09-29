package com.project.back.service;

import org.springframework.http.ResponseEntity;

import com.project.back.dto.request.faq.PostFaqRequestDto;
import com.project.back.dto.request.faq.PutFaqRequestDto;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.faq.GetFaqListResponseDto;

public interface FaqService {
    
    // 자주하는 질문 리스트 보기
    ResponseEntity<? super GetFaqListResponseDto> getFaqList();

    // 자주하는 질문 카테고리 리스트 보기
    ResponseEntity<? super GetFaqListResponseDto> getFaqCategoryList(String faqCategory);

    // 자주하는 질문 작성하기
    ResponseEntity<ResponseDto> postFaq(PostFaqRequestDto dto, String userId);

    // 자주하는 질문 수정하기
    ResponseEntity<ResponseDto> putFaq(PutFaqRequestDto dto, int faqNumber, String userId);

    // 자주하는 질문 삭제하기
    ResponseEntity<ResponseDto> deleteFaq(int faqNumber, String userId);
}
