package com.project.back.service;

import org.springframework.http.ResponseEntity;

import com.project.back.dto.request.notice.PostNoticeRequestDto;
import com.project.back.dto.request.notice.PutNoticeRequestDto;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.notice.GetNoticeDetailResponseDto;
import com.project.back.dto.response.notice.GetNoticeListResponseDto;

public interface NoticeService {

    // 공지사항 전체 리스트 보기
    ResponseEntity<? super GetNoticeListResponseDto> getNoticeList();

    // 공지사항 상세 보기
    ResponseEntity<? super GetNoticeDetailResponseDto> getNoticeDetail(int noticeNumber);

    // 공지사항 작성하기
    ResponseEntity<ResponseDto> postNotice(PostNoticeRequestDto dto, String userId);

    // 공지사항 수정하기
    ResponseEntity<ResponseDto> putNotice (PutNoticeRequestDto dto, int noticeNumber, String userId);

    // 공지사항 조회수 증가
    ResponseEntity<ResponseDto> increaseViewCount(int noticeNumber);

    // 공지사항 삭제하기
    ResponseEntity<ResponseDto> deleteNotice(int noticeNumber, String userId);
    
}
