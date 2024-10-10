package com.project.back.service;

import org.springframework.http.ResponseEntity;

import com.project.back.dto.request.qna.PutQnaCommentRequestDto;
import com.project.back.dto.request.qna.PostQnaRequestDto;
import com.project.back.dto.request.qna.PutQnaRequestDto;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.qna.GetQnaDetailResponseDto;
import com.project.back.dto.response.qna.GetQnaListResponseDto;
import com.project.back.dto.response.qna.GetQnaMyListResponseDto;

public interface QnaService {

    // 문의사항 전체 리스트 보기
    ResponseEntity<? super GetQnaListResponseDto> getQnaList();

    // 문의사항 리스트 검색하기
    ResponseEntity<? super GetQnaListResponseDto> getQnaSearchList(String searchWord);

    // 문의사항 카테고리 리스트 보기
    ResponseEntity<? super GetQnaListResponseDto> getQnaCategoryList(String qnaCategory);

    // 문의사항 카테고리 리스트에서 검색하기
    ResponseEntity<? super GetQnaListResponseDto> getQnaCategorySearchList(String qnaCategory, String searchWord);

    // 문의사항 상세 보기
    ResponseEntity<? super GetQnaDetailResponseDto> getQnaDetail(int qnaNumber);

    // 나의 문의내역 리스트 보기
    ResponseEntity<? super GetQnaMyListResponseDto> getQnaMyList(String userId);
     
    // 문의사항 작성하기
    ResponseEntity<ResponseDto> postQna(PostQnaRequestDto dto, String userId);

    // 문의사항 댓글 달기
    ResponseEntity<ResponseDto> putQnaComment(PutQnaCommentRequestDto dto, int qnaNumber);

    // 문의사항 수정하기
    ResponseEntity<ResponseDto> putQna(PutQnaRequestDto dto, int qnaNumber, String userId);

    // 문의사항 삭제하기
    ResponseEntity<ResponseDto> deleteQna(int qnaNumber, String userId);

}
