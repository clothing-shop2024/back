package com.project.back.service.implementation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.back.dto.request.cloth.PostClothInfoRequestDto;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.cloth.GetClothInfoResponseDto;
import com.project.back.dto.response.cloth.favorite.GetFavoriteCheckResponseDto;
import com.project.back.dto.response.cloth.favorite.GetFavoriteClothListResponseDto;
import com.project.back.entity.ClothEntity;
import com.project.back.entity.FavoriteClothEntity;
import com.project.back.repository.ClothInfoRepository;
import com.project.back.repository.ClothRepository;
import com.project.back.repository.FavoriteClothRepository;
import com.project.back.repository.UserRepository;
import com.project.back.repository.resultSet.GetClothFavoriteItemResultSet;
import com.project.back.service.ClothService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClothServiceImplementation implements ClothService {

    private final ClothInfoRepository clothInfoRepository;
    private final ClothRepository clothRepository;
    private final FavoriteClothRepository favoriteClothRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<ResponseDto> postClothInfo(PostClothInfoRequestDto dto, String userId) {
        try {

            boolean isExistUser = userRepository.existsByUserId(userId);

            // 로그인을 하지 않을 경우 로그인화면으로 이동 기능

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetClothInfoResponseDto> getClothInfo(Integer clothNumber) {
        try {
            ClothEntity clothEntity = clothInfoRepository.findByClothNumber(clothNumber);

            return GetClothInfoResponseDto.success(clothEntity, null, null);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    // @Override
    // public ResponseEntity<? super GetClothNumberResponseDto>
    // getClothNumber(String userId) {
    // try {
    // ClothEntity clothEntity = clothRepository.findByClothNumber(userId);
    // if (clothEntity == null)
    // return ResponseDto.authorizationFailed();

    // return GetClothNumberResponseDto.success(clothEntity);
    // } catch (Exception exception) {
    // exception.printStackTrace();
    // return ResponseDto.databaseError();
    // }
    // }

    // 찜 목록
    @Override
    public ResponseEntity<ResponseDto> postFavorite(String userId, Integer clothNumber) {
        try {
            boolean isExistUser = userRepository.existsByUserId(userId);
            if (!isExistUser)
                return ResponseDto.noExistUser();

            // 옷이 존재하는지 확인
            boolean isExistCloth = clothRepository.existsByClothNumber(clothNumber);
            if (!isExistCloth)
                return ResponseDto.noExistReservation();

            FavoriteClothEntity favoriteClothEntity = new FavoriteClothEntity(userId, clothNumber);
            favoriteClothRepository.save(favoriteClothEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetFavoriteClothListResponseDto> getFavoriteList(String userId) {
        try {
            List<GetClothFavoriteItemResultSet> resultSets = clothRepository.getFavoriteList(userId);

            return GetFavoriteClothListResponseDto.success(resultSets);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetFavoriteCheckResponseDto> getFavoriteCheck(String userId, Integer clothNumber) {
        try {
            boolean isFavoriteStatus = favoriteClothRepository
                    .existsByUserIdAndClothNumber(userId, clothNumber);
            if (!isFavoriteStatus)
                return ResponseDto.noExistUser();

            FavoriteClothEntity favoriteClothEntity = favoriteClothRepository
                    .findByUserIdAndClothNumber(userId, clothNumber);

            return GetFavoriteCheckResponseDto.success(favoriteClothEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> deleteFavorite(String userId, Integer clothNumber) {
        try {
            boolean isExistUser = userRepository.existsByUserId(userId);
            if (!isExistUser)
                return ResponseDto.noExistUser();

            boolean isExistCloth = clothRepository.existsByClothNumber(clothNumber);
            if (!isExistCloth)
                return ResponseDto.noExistReservation();

            FavoriteClothEntity favoriteClothEntity = favoriteClothRepository
                    .findByUserIdAndClothNumber(userId, clothNumber);
            if (favoriteClothEntity == null)
                return ResponseDto.authorizationFailed();

            favoriteClothRepository.delete(favoriteClothEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }
}
