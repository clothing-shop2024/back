package com.project.back.service.implementation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.back.dto.request.notice.PostNoticeRequestDto;
import com.project.back.dto.request.notice.PutNoticeRequestDto;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.notice.GetNoticeDetailResponseDto;
import com.project.back.dto.response.notice.GetNoticeListResponseDto;
import com.project.back.entity.NoticeEntity;
import com.project.back.entity.UserEntity;
import com.project.back.repository.NoticeRepository;
import com.project.back.repository.UserRepository;
import com.project.back.service.NoticeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImplementation implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;

    // 공지사항 전체 리스트 보기
    @Override
    public ResponseEntity<? super GetNoticeListResponseDto> getNoticeList() {

        try {

            List<NoticeEntity> noticeEntities = noticeRepository.findByOrderByNoticeNumberDesc();

            return GetNoticeListResponseDto.success(noticeEntities);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

    }

    // 공지사항 상세 보기
    @Override
    public ResponseEntity<? super GetNoticeDetailResponseDto> getNoticeDetail(int noticeNumber) {

        try {

            NoticeEntity noticeEntity = noticeRepository.findByNoticeNumber(noticeNumber);

            if (noticeEntity == null) return ResponseDto.noExistBoard();

            return GetNoticeDetailResponseDto.success(noticeEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

    }

    // 공지사항 작성하기
    @Override
    public ResponseEntity<ResponseDto> postNotice(PostNoticeRequestDto dto, String userId) {

        try {

            // boolean isExistUser = userRepository.existsById(userId);
            // if (!isExistUser) return ResponseDto.authenticationFailed();

            UserEntity userEntity = userRepository.findUserRoleByUserId(userId);
            boolean isAdmin = "ROLE_ADMIN".equals(userEntity.getUserRole());
            if (!isAdmin) return ResponseDto.authenticationFailed();

            NoticeEntity noticeEntity = new NoticeEntity(dto, userId);

            noticeRepository.save(noticeEntity);
            
            return ResponseDto.success();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

    }

    // 공지사항 수정하기
    @Override
    public ResponseEntity<ResponseDto> putNotice(PutNoticeRequestDto dto, int noticeNumber, String userId) {

        try {

            NoticeEntity noticeEntity = noticeRepository.findByNoticeNumber(noticeNumber);
            if (noticeEntity == null) return ResponseDto.noExistBoard();

            UserEntity userEntity = userRepository.findUserRoleByUserId(userId);
            boolean isAdmin = "ROLE_ADMIN".equals(userEntity.getUserRole());
            if (!isAdmin) return ResponseDto.authenticationFailed();

            noticeRepository.save(noticeEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    // 조회수
    @Override
    public ResponseEntity<ResponseDto> increaseViewCount(int noticeNumber) {

        try {

            NoticeEntity noticeEntity = noticeRepository.findByNoticeNumber(noticeNumber);
            if (noticeEntity == null) return ResponseDto.noExistBoard();

            noticeEntity.increaseViewCount();
            noticeRepository.save(noticeEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    // 공지사항 삭제하기
    @Override
    public ResponseEntity<ResponseDto> deleteNotice(int noticeNumber, String userId) {

        try {

            NoticeEntity noticeEntity = noticeRepository.findByNoticeNumber(noticeNumber);
            if (noticeEntity == null) return ResponseDto.noExistBoard();

            UserEntity userEntity = userRepository.findUserRoleByUserId(userId);
            boolean isAdmin = "ROLE_ADMIN".equals(userEntity.getUserRole());
            if (!isAdmin) return ResponseDto.authenticationFailed();

            noticeRepository.delete(noticeEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }
    
}
