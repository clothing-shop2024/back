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

    @Override
    public ResponseEntity<ResponseDto> postNotice(PostNoticeRequestDto dto, String userId) {

        try {

            UserEntity userRole = userRepository.findUserRoleByUserId(userId);
            if (UserEntity.userRole != "ROLE_ADMIN") return ResponseDto.authenticationFailed();

            NoticeEntity noticeEntity = new NoticeEntity(dto, userId);

            noticeRepository.save(noticeEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> putNotice(PutNoticeRequestDto dto, int noticeNumber, String userId) {

        try {

            NoticeEntity noticeEntity = noticeRepository.findByNoticeNumber(noticeNumber);
            if (noticeEntity == null) return ResponseDto.noExistBoard();

            UserEntity userRole = userRepository.findUserRoleByUserId(userId);
            if (UserEntity.userRole != "ROLE_ADMIN") return ResponseDto.authenticationFailed();

            noticeRepository.save(noticeEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

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

    @Override
    public ResponseEntity<ResponseDto> deleteNotice(int noticeNumber, String userId) {

        try {

            NoticeEntity noticeEntity = noticeRepository.findByNoticeNumber(noticeNumber);
            if (noticeEntity == null) return ResponseDto.noExistBoard();

            UserEntity userRole = userRepository.findUserRoleByUserId(userId);
            if (UserEntity.userRole != "ROLE_ADMIN") return ResponseDto.authenticationFailed();

            noticeRepository.delete(noticeEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }
    
}
