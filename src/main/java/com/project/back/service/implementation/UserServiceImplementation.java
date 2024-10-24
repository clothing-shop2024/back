package com.project.back.service.implementation;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.back.common.util.EmailAuthNumberUtil;
import com.project.back.dto.request.auth.EmailAuthRequestDto;
import com.project.back.dto.request.user.DeleteUserRequestDto;
import com.project.back.dto.request.user.PatchUserGradeRequestDto;
import com.project.back.dto.request.user.PatchUserInfoRequestDto;
import com.project.back.dto.request.user.PostUserPointRequestDto;
import com.project.back.dto.request.user.PutEmailModifyRequestDto;
import com.project.back.dto.request.user.PutPasswordModifyRequestDto;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.user.GetMyInfoResponseDto;
import com.project.back.dto.response.user.GetSignInUserResponseDto;
import com.project.back.entity.EmailAuthNumberEntity;
import com.project.back.entity.UserEntity;
import com.project.back.provider.MailProvider;
import com.project.back.repository.EmailAuthNumberRepository;
import com.project.back.repository.UserRepository;
import com.project.back.service.UserService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final MailProvider mailProvider;
    private final EmailAuthNumberRepository emailAuthNumberRepository;

    @Override
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String userId) {

        UserEntity userEntity = null;

        try {

            userEntity = userRepository.findByUserId(userId);
            if (userEntity == null)
                return ResponseDto.authenticationFailed();

            return GetSignInUserResponseDto.success(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetMyInfoResponseDto> getMyInfo(String userId) {
        UserEntity userEntity = null;

        try {

            userEntity = userRepository.findByUserId(userId);
            if (userEntity == null)
                return ResponseDto.authenticationFailed();

            return GetMyInfoResponseDto.success(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> patchUserInfo(PatchUserInfoRequestDto dto, String userId) {
        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null)
                return ResponseDto.noExistUser();

            String updateId = userEntity.getUserId();
            boolean isEquals = userId.equals(updateId);
            if (!isEquals)
                return ResponseDto.authorizationFailed();

            userEntity.update(dto);
            userRepository.save(userEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> deleteUserInfo(DeleteUserRequestDto dto, String userId) {
        try {

            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null)
                return ResponseDto.noExistInfo();

            userRepository.delete(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> updateUserGrade(PatchUserGradeRequestDto dto, String userId) {
        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) {
                return ResponseDto.noExistUser(); // 사용자 존재 여부 확인
            }

            String newGrade = dto.getNewGrade();
            // 등급 유효성 검사 (선택적)
            if (newGrade == null || newGrade.isEmpty()) {
                return ResponseDto.invalidRequest();
            }

            // 등급 업데이트
            userEntity.setGrade(newGrade);
            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> addUserPoints(PostUserPointRequestDto dto, String userId) {

        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
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
    public ResponseEntity<ResponseDto> setUserPoints(PostUserPointRequestDto dto, String userId) {

        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) {
                return ResponseDto.noExistUser();
            }

            int newPoints = dto.getPoints();
            if (newPoints < 0) {
                return ResponseDto.invalidRequest();
            }

            userEntity.setPoints(newPoints);
            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> putPasswordModify(PutPasswordModifyRequestDto dto, String userId) {
        try {

            String password = dto.getPassword();

            UserEntity userEntity = userRepository.findByUserId(userId);
            System.out.println(userId);
            if (userEntity == null)
                return ResponseDto.noExistUser();

            boolean isMatched = userRepository.existsById(userId);
            if (!isMatched)
                return ResponseDto.authenticationFailed();

            String encodedPassword = passwordEncoder.encode(password);

            dto.setPassword(encodedPassword);
            userEntity.findModify(dto);
            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> emailAuth(EmailAuthRequestDto dto) {
        try {

            String userEmail = dto.getUserEmail();
            boolean existedEmail = userRepository.existsByUserEmail(userEmail);
            if (existedEmail)
                return ResponseDto.duplicatedEmail();

            String authNumber = EmailAuthNumberUtil.createCodeNumber();

            EmailAuthNumberEntity emailAuthNumberEntity = new EmailAuthNumberEntity(userEmail, authNumber);

            emailAuthNumberRepository.save(emailAuthNumberEntity);

            mailProvider.mailAuthSend(userEmail, authNumber);

        } catch (MessagingException exception) {
            exception.printStackTrace();
            return ResponseDto.mailSendFailed();
        }

        catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> putEmailModify(PutEmailModifyRequestDto dto, String userId) {
        try {

            String userEmail = dto.getUserEmail();
            String userNumber = dto.getAuthNumber();

            boolean isMatched = emailAuthNumberRepository.existsByUserEmailAndAuthNumber(userEmail, userNumber);

            if (!isMatched)
                return ResponseDto.authenticationFailed();

            UserEntity userEntity = userRepository.findByUserId(userId);

            userEntity.emailModify(dto);
            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

}
