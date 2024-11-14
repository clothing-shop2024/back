package com.project.back.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.back.dto.request.qna.PutQnaCommentRequestDto;
import com.project.back.dto.request.qna.PostQnaRequestDto;
import com.project.back.dto.request.qna.PutQnaRequestDto;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.qna.GetQnaDetailResponseDto;
import com.project.back.dto.response.qna.GetQnaListResponseDto;
import com.project.back.service.QnaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/shop/qna")
@RequiredArgsConstructor
public class QnaController {

    private final QnaService qnaService;

    // 문의사항 전체 게시물 리스트 불러오기
    @GetMapping("/list")
    public ResponseEntity<? super GetQnaListResponseDto> getQnaList () {

        ResponseEntity<? super GetQnaListResponseDto> response = qnaService.getQnaList();

        return response;

    }

    // 문의사항 검색 리스트 불러오기
    @GetMapping("/list/search")
    public ResponseEntity<? super GetQnaListResponseDto> getQnaSearchList (
        @RequestParam("word") String word
    ) {

        ResponseEntity<? super GetQnaListResponseDto> response = qnaService.getQnaSearchList(word);

        return response;

    }

    // 문의사항 카테고리 필터 보기
    @GetMapping("/list/category/{qnaCategory}")
    public ResponseEntity<? super GetQnaListResponseDto> getQnaCategoryList (
        @PathVariable("qnaCategory") String qnaCategory
    ) {

        ResponseEntity<? super GetQnaListResponseDto> response = qnaService.getQnaCategoryList(qnaCategory);

        return response;

    }

    // 문의사항 카테고리 필터에 검색하기
    @GetMapping("/list/category/{qnaCategory}/search")
    public ResponseEntity<? super GetQnaListResponseDto> getQnaSearchList (
        @PathVariable("qnaCategory") String qnaCategory,
        @RequestParam("word") String word
    ) {

        ResponseEntity<? super GetQnaListResponseDto> response = qnaService.getQnaCategorySearchList(qnaCategory, word);

        return response;

    }

    // 문의사항 상세 보기
    @GetMapping("/list/{qnaNumber}")
    public ResponseEntity<? super GetQnaDetailResponseDto> getQnaDetail (
        @PathVariable("qnaNumber") int qnaNumber
    ) {

        ResponseEntity<? super GetQnaDetailResponseDto> response = qnaService.getQnaDetail(qnaNumber);

        return response;

    }

    // 문의사항 작성하기
    @PostMapping("/regist")
    ResponseEntity<ResponseDto> postQna (
        @RequestBody @Valid PostQnaRequestDto requestDto,
        @AuthenticationPrincipal String userId
    ) {

        ResponseEntity<ResponseDto> response = qnaService.postQna(requestDto, userId);

        return response;

    }

    // 문의사항 답글 작성
    @PutMapping("/{qnaNumber}/comment")
    public ResponseEntity<ResponseDto> putQnaComment (
        @RequestBody @Valid PutQnaCommentRequestDto requestBody,
        @PathVariable("qnaNumber") int qnaNumber
    ) {

        ResponseEntity<ResponseDto> response = qnaService.putQnaComment(requestBody, qnaNumber);
        
        return response;

    }

    // 문의사항 수정하기
    @PutMapping("/{qnaNumber}/modify")
    public ResponseEntity<ResponseDto> putQna (
        @RequestBody @Valid PutQnaRequestDto requestDto,
        @PathVariable("qnaNumber") int qnaNumber,
        @AuthenticationPrincipal String userId
    ) {

        ResponseEntity<ResponseDto> response = qnaService.putQna(requestDto, qnaNumber, userId);

        return response;

    }
    
    // 문의사항 게시물 삭제하기
    @DeleteMapping("/{qnaNumber}/delete")
    public ResponseEntity<ResponseDto> deleteQnaBoard (
        @PathVariable("qnaNumber") int qnaNumber,
        @AuthenticationPrincipal String userId
    ) {

        ResponseEntity<ResponseDto> response = qnaService.deleteQna(qnaNumber, userId);

        return response;

    }

}
