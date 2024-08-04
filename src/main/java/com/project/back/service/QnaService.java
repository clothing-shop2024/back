package com.project.back.service;

import org.springframework.http.ResponseEntity;

import com.project.back.dto.request.qna.PostQnaCommentRequestDto;
import com.project.back.dto.request.qna.PostQnaRequestDto;
import com.project.back.dto.request.qna.PutQnaRequestDto;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.qna.GetQnaDetailResponseDto;
import com.project.back.dto.response.qna.GetQnaListResponseDto;
import com.project.back.dto.response.qna.GetQnaMyListResponseDto;
import com.project.back.dto.response.qna.GetQnaSearchResponseDto;

public interface QnaService {

    // 문의사항 리스트 보기
    ResponseEntity<? super GetQnaListResponseDto> getQnaList();

    // 문의사항 리스트 검색하기
    ResponseEntity<? super GetQnaSearchResponseDto> getQnaSearchList(String searchWord);

    // 문의사항 보기
    ResponseEntity<? super GetQnaDetailResponseDto> getQnaDetail(int qnaNumber);

    // 나의 문의내역
    ResponseEntity<? super GetQnaMyListResponseDto> getQnaMyList(String userId);
     
    // 문의사항 작성하기
    ResponseEntity<ResponseDto> postQna(PostQnaRequestDto dto, String userId);

    // 문의사항 댓글 달기
    ResponseEntity<ResponseDto> postQnaComment(PostQnaCommentRequestDto dto, int qnaNumber);

    // 문의사항 수정하기
    ResponseEntity<ResponseDto> putQna(PutQnaRequestDto dto, int qnaNumber, String userId);

    // 문의사항 조회수 증가
    ResponseEntity<ResponseDto> increaseViewCount(int qnaNumber);

    // 문의사항 삭제하기
    ResponseEntity<ResponseDto> deleteQna(int qnaNumber, String userId);

}
