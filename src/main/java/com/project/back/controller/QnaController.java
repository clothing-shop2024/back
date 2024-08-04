package com.project.back.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.back.dto.request.qna.PostQnaCommentRequestDto;
import com.project.back.dto.request.qna.PostQnaRequestDto;
import com.project.back.dto.request.qna.PutQnaRequestDto;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.qna.GetQnaDetailResponseDto;
import com.project.back.dto.response.qna.GetQnaListResponseDto;
import com.project.back.dto.response.qna.GetQnaMyListResponseDto;
import com.project.back.dto.response.qna.GetQnaSearchResponseDto;
import com.project.back.service.QnaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/shop/qna")
@RequiredArgsConstructor
public class QnaController {

    private final QnaService qnaService;

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
    @PostMapping("/{qnaNumber}/comment")
    public ResponseEntity<ResponseDto> postQnaComment (
        @RequestBody @Valid PostQnaCommentRequestDto requestBody,
        @PathVariable("qnaNumber") int qnaNumber
    ) {

        ResponseEntity<ResponseDto> response = qnaService.postQnaComment(requestBody, qnaNumber);
        
        return response;

    }

    // 문의사항 전체 게시물 리스트 불러오기
    @GetMapping("/list")
    public ResponseEntity<? super GetQnaListResponseDto> getQnaList () {

        ResponseEntity<? super GetQnaListResponseDto> response = qnaService.getQnaList();

        return response;

    }

    // 문의사항 검색 리스트 불러오기
    @GetMapping("/list/search")
    public ResponseEntity<? super GetQnaSearchResponseDto> getQnaSearchList (
        @RequestParam("word") String word
    ) {

        ResponseEntity<? super GetQnaSearchResponseDto> response = qnaService.getQnaSearchList(word);

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

    // 나의 문의내역 보기
    @GetMapping("/mylist")
    public ResponseEntity<? super GetQnaMyListResponseDto> getQnaMylist (
        @AuthenticationPrincipal String userId
    ) {

        ResponseEntity<? super GetQnaMyListResponseDto> response = qnaService.getQnaMyList(userId);

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
    
    // 문의사항 게시물 조회수 증가
    @PatchMapping("/{qnaNumber}/increase-view-count")
    public ResponseEntity<ResponseDto> increaseViewCount (
        @PathVariable("qnaNumber") int qnaNumber
    ) {

        ResponseEntity<ResponseDto> response = qnaService.increaseViewCount(qnaNumber);

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
