package com.project.back.service.implementation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.back.dto.request.user.PatchUserGradeRequestDto;
import com.project.back.dto.request.user.PatchUserPointsRequestDto;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.user.GetAdminUserListResponseDto;
import com.project.back.dto.response.user.GetMyInfoResponseDto;
import com.project.back.entity.UserEntity;
import com.project.back.repository.UserRepository;
import com.project.back.service.AdminService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImplementation implements AdminService {
    
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super GetAdminUserListResponseDto> getUserDescList(String userId) {
        
        try {

            List<UserEntity> userEntities = userRepository.findByOrderByJoinDateDesc();

            return GetAdminUserListResponseDto.success(userEntities);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }
    
    @Override
    public ResponseEntity<? super GetAdminUserListResponseDto> getUserAscList(String userId) {
        
        try {

            List<UserEntity> userEntities = userRepository.findByOrderByJoinDateAsc();

            return GetAdminUserListResponseDto.success(userEntities);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetAdminUserListResponseDto> getUserIdSearchList(String userId, String searchWord) {
        
        try {

            List<UserEntity> userEntities = userRepository.findByUserIdContainsOrderByJoinDateDesc(searchWord);

            return GetAdminUserListResponseDto.success(userEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetAdminUserListResponseDto> getUserNameSearchList(String userId, String searchWord) {

        try {
            
            List<UserEntity> userEntities = userRepository.findByUserNameContainsOrderByJoinDateDesc(searchWord);

            return GetAdminUserListResponseDto.success(userEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetAdminUserListResponseDto> getUserGradeSearchList(String userId, String searchWord) {

        try {
    
            List<UserEntity> userEntities = userRepository.findByGradeContainsOrderByJoinDateDesc(searchWord);

            return GetAdminUserListResponseDto.success(userEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetMyInfoResponseDto> getUserDetail(String nickname) {
        
        try {

            UserEntity userEntity = userRepository.findByNickname(nickname);

            if (userEntity == null) return ResponseDto.noExistUser();

            return GetMyInfoResponseDto.success(userEntity);

        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> patchUserGrade(PatchUserGradeRequestDto dto, String nickname) {
        
        try {
            UserEntity userEntity = userRepository.findByNickname(nickname);

            if (userEntity == null) {
                return ResponseDto.noExistUser();
            }
            
            String newGrade = dto.getNewGrade();
            if (newGrade == null || newGrade.isEmpty()) {
                return ResponseDto.invalidRequest();
            }

            userEntity.setGrade(newGrade);
            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> patchUserPoints(PatchUserPointsRequestDto dto, String nickname) {
        
        try {
            UserEntity userEntity = userRepository.findByNickname(nickname);

            if (userEntity == null) {
                return ResponseDto.noExistUser();
            }
            
            int pointsAdd = dto.getPoints();
            if (pointsAdd < 0) {
                return ResponseDto.invalidRequest();
            }

            userEntity.addPoints(pointsAdd);
            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> deleteUser(String nickname) {
        
        try {

            UserEntity userEntity = userRepository.findByNickname(nickname);

            if (userEntity == null) return ResponseDto.noExistUser();

            userRepository.delete(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }
}
