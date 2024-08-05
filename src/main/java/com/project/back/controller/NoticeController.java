package com.project.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.back.dto.request.notice.PostNoticeRequestDto;
import com.project.back.dto.request.notice.PutNoticeRequestDto;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.notice.GetNoticeDetailResponseDto;
import com.project.back.dto.response.notice.GetNoticeListResponseDto;
import com.project.back.service.NoticeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/shop/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    // 공지사항 전체 리스트 보기
    @GetMapping("/list")
    public ResponseEntity<? super GetNoticeListResponseDto> getNoticeList () {

        ResponseEntity<? super GetNoticeListResponseDto> response = noticeService.getNoticeList();

        return response;

    }

    // 공지사항 상세 게시물 불러오기
    @GetMapping("/list/{noticeNumber}")
    public ResponseEntity<? super GetNoticeDetailResponseDto> getNoticeDetail (
        @PathVariable("noticeNumber") int noticeNumber
    ) {
        
        ResponseEntity<? super GetNoticeDetailResponseDto> response = noticeService.getNoticeDetail(noticeNumber);

        return response;

    }

    // 공지사항 작성하기
    @PostMapping("/regist")
    ResponseEntity<ResponseDto> postNotice (
        @RequestBody @Valid PostNoticeRequestDto requestBody,
        @AuthenticationPrincipal String userId
    ) {
        
        ResponseEntity<ResponseDto> response = noticeService.postNotice(requestBody, userId);

        return response;

    }

    // 공지사항 수정하기
    @PutMapping("/{noticeNumber}/modify")
    public ResponseEntity<ResponseDto> putNotice (
        @RequestBody @Valid PutNoticeRequestDto requestBody,
        @PathVariable("noticeNumber") int noticeNumber,
        @AuthenticationPrincipal String userId
    ) {
        
        ResponseEntity<ResponseDto> response = noticeService.putNotice(requestBody, noticeNumber, userId);

        return response;

    }

    // 공지사항 게시물 조회수 증가
    @PatchMapping("/{noticeNumber}/increase-view-count")
    public ResponseEntity<ResponseDto> increaseViewCount (
        @PathVariable("noticeNumber") int noticeNumber
    ) {
        
        ResponseEntity<ResponseDto> response = noticeService.increaseViewCount(noticeNumber);

        return response;

    }

    // 공지사항 게시물 삭제하기
    @DeleteMapping("/{noticeNumber}/delete")
    public ResponseEntity<ResponseDto> deleteNotice (
        @PathVariable("noticeNumber") int noticeNumber,
        @AuthenticationPrincipal String userId
    ) {
        
        ResponseEntity<ResponseDto> response = noticeService.deleteNotice(noticeNumber, userId);

        return response;

    }
    
}
