package com.project.back.service.implementation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.cloth.GetClothStockListResponseDto;
import com.project.back.entity.UserEntity;
import com.project.back.repository.ClothStockRepository;
import com.project.back.repository.UserRepository;
import com.project.back.repository.resultSet.ClothStockResultSet;
import com.project.back.service.ClothStockService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClothStockServiceImplementation implements ClothStockService{
    
    private final UserRepository userRepository;
    private final ClothStockRepository clothStockRepository;

    @Override
    public ResponseEntity<? super GetClothStockListResponseDto> getClothStockList(String userId, String clothId) {
        
        try {

            UserEntity userEntity = userRepository.findUserRoleByUserId(userId);
            boolean isAdmin = "ROLE_ADMIN".equals(userEntity.getUserRole());
            if (!isAdmin) return ResponseDto.authenticationFailed();

            List<ClothStockResultSet> clothStockResultSets = clothStockRepository.getClothStockList(clothId);

            return GetClothStockListResponseDto.success(clothStockResultSets);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }
}
