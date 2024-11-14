package com.project.back.service.implementation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.back.dto.request.qna.PutQnaCommentRequestDto;
import com.project.back.dto.request.qna.PostQnaRequestDto;
import com.project.back.dto.request.qna.PutQnaRequestDto;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.qna.GetQnaDetailResponseDto;
import com.project.back.dto.response.qna.GetQnaListResponseDto;
import com.project.back.entity.QnaEntity;
import com.project.back.entity.UserEntity;
import com.project.back.repository.QnaRepository;
import com.project.back.repository.UserRepository;
import com.project.back.service.QnaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaServiceImplementation implements QnaService {

    private final QnaRepository qnaRepository;
    private final UserRepository userRepository;

    // 문의사항 리스트 보기
    @Override
    public ResponseEntity<? super GetQnaListResponseDto> getQnaList() {

        try {

            List<QnaEntity> qnaEntities = qnaRepository.findByOrderByQnaNumberDesc();
            
            return GetQnaListResponseDto.success(qnaEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        
    }

    // 문의사항 검색 리스트 보기
    @Override
    public ResponseEntity<? super GetQnaListResponseDto> getQnaSearchList(String searchWord) {
        
        try {

            List<QnaEntity> qnaEntities = qnaRepository.findByQnaWriterIdContainsOrderByQnaNumberDesc(searchWord);

            return GetQnaListResponseDto.success(qnaEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    // 문의사항 카테고리 필터 보기
    @Override
    public ResponseEntity<? super GetQnaListResponseDto> getQnaCategoryList(String qnaCategory) {

        try {

            List<QnaEntity> qnaEntities = qnaRepository.findByQnaCategoryContainsOrderByQnaNumberDesc(qnaCategory);

            return GetQnaListResponseDto.success(qnaEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    // 문의사항 카테고리 리스트에서 검색하기
    @Override
    public ResponseEntity<? super GetQnaListResponseDto> getQnaCategorySearchList(String qnaCategory, String searchWord) {

        try {

            List<QnaEntity> qnaEntities = qnaRepository.findByQnaCategoryAndQnaWriterIdContainsOrderByQnaNumberDesc(qnaCategory, searchWord);

            return GetQnaListResponseDto.success(qnaEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    // 문의사항 상세 보기
    @Override
    public ResponseEntity<? super GetQnaDetailResponseDto> getQnaDetail(int qnaNumber) {
        
        try {

            QnaEntity qnaEntities = qnaRepository.findByQnaNumber(qnaNumber);
            if (qnaEntities == null) return ResponseDto.noExistBoard();

            return GetQnaDetailResponseDto.success(qnaEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    // 문의사항 작성하기
    @Override
    public ResponseEntity<ResponseDto> postQna(PostQnaRequestDto dto, String userId) {
        
        try {

            boolean isExistUser = userRepository.existsByUserId(userId);
            if (!isExistUser) return ResponseDto.authenticationFailed();

            QnaEntity qnaEntity = new QnaEntity(dto, userId);
            qnaRepository.save(qnaEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    // 문의사항 답글 작성하기
    @Override
    public ResponseEntity<ResponseDto> putQnaComment(PutQnaCommentRequestDto dto, int qnaNumber) {
        
        try {

            QnaEntity qnaEntities = qnaRepository.findByQnaNumber(qnaNumber);
            if (qnaEntities == null) return ResponseDto.noExistBoard();

            String qnaComment = dto.getQnaComment();
            qnaEntities.setStatus(true);
            qnaEntities.setQnaComment(qnaComment);
            
            qnaRepository.save(qnaEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    // 문의사항 수정하기
    @Override
    public ResponseEntity<ResponseDto> putQna(PutQnaRequestDto dto, int qnaNumber, String userId) {
        
        try {

            QnaEntity qnaEntities =qnaRepository.findByQnaNumber(qnaNumber);
            if (qnaEntities == null) return ResponseDto.noExistBoard();

            String writerId = qnaEntities.getQnaWriterId();
            boolean isWriter = userId.equals(writerId);
            if (!isWriter) return ResponseDto.authorizationFailed();

            // if (!qnaEntities.isStatus()) return ResponseDto.authorizationFailed();

            qnaEntities.update(dto);

            qnaRepository.save(qnaEntities);


        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    // 문의사항 삭제하기
    @Override
    public ResponseEntity<ResponseDto> deleteQna(int qnaNumber, String userId) {
        
        try {

            QnaEntity qnaEntities = qnaRepository.findByQnaNumber(qnaNumber);
            if (qnaEntities == null) return ResponseDto.noExistBoard();

            String writerId = qnaEntities.getQnaWriterId();
            boolean isWriter = userId.equals(writerId);
            UserEntity userEntity = userRepository.findUserRoleByUserId(userId);
            boolean isAdmin = "ROLE_ADMIN".equals(userEntity.getUserRole());
            if (!isWriter && !isAdmin) return ResponseDto.authorizationFailed();
            
            qnaRepository.delete(qnaEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

}
