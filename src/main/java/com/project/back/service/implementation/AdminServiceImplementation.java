package com.project.back.service.implementation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.user.GetAdminUserListResponseDto;
import com.project.back.entity.UserEntity;
import com.project.back.repository.UserRepository;
import com.project.back.service.AdminService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImplementation implements AdminService {
    
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super GetAdminUserListResponseDto> getUserList(String userId) {
        
        try {

            List<UserEntity> userEntities = userRepository.findByOrderByJoinDateDesc();

            return GetAdminUserListResponseDto.success(userEntities);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }
}
