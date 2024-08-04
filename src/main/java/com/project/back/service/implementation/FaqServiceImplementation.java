package com.project.back.service.implementation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.back.dto.request.faq.PostFaqRequestDto;
import com.project.back.dto.request.faq.PutFaqRequestDto;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.faq.GetFaqListResponseDto;
import com.project.back.entity.FaqEntity;
import com.project.back.entity.UserEntity;
import com.project.back.repository.FaqRepository;
import com.project.back.repository.UserRepository;
import com.project.back.service.FaqService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FaqServiceImplementation implements FaqService {
    
    private final FaqRepository faqRepository;
    private final UserRepository userRepository;

    // 자주하는 질문 전체 리스트 보기
    @Override
    public ResponseEntity<? super GetFaqListResponseDto> getFaqList () {

        try {

            List<FaqEntity> faqEntities = faqRepository.findByOrderByFaqNumberDesc();
            
            return GetFaqListResponseDto.success(faqEntities);

        }

        catch(Exception exception) {
        exception.printStackTrace();
        return ResponseDto.databaseError();
        }
    }

    // 자주하는 질문 작성하기
    @Override
    public ResponseEntity<ResponseDto> postFaq (PostFaqRequestDto dto, String userId) {

        try {

            UserEntity userRole = userRepository.findUserRoleByUserId(userId);
            if (UserEntity.userRole != "ROLE_ADMIN") return ResponseDto.authenticationFailed();

            FaqEntity faqEntity = new FaqEntity(dto);
            faqRepository.save(faqEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }

    // 자주하는 질문 수정하기
    @Override
    public ResponseEntity<ResponseDto> putFaq (PutFaqRequestDto dto, int faqNumber, String userId) {

        try {

            UserEntity userRole = userRepository.findUserRoleByUserId(userId);
            if (UserEntity.userRole != "ROLE_ADMIN") return ResponseDto.authenticationFailed();

            FaqEntity faqEntity = faqRepository.findByFaqNumber(faqNumber);
            if (faqEntity == null) return ResponseDto.noExistBoard();

            faqEntity.update(dto);

            faqRepository.save(faqEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    // 자주하는 질문 삭제하기
    @Override
    public ResponseEntity<ResponseDto> deleteFaq(int faqNumber, String userId) {
        
        try {

            FaqEntity faqEntity = faqRepository.findByFaqNumber(faqNumber);
            if (faqEntity == null) return ResponseDto.noExistBoard();

            faqRepository.delete(faqEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }
}
