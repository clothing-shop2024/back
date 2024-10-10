package com.project.back.service.implementation;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.back.common.util.EmailAuthNumberUtil;
import com.project.back.dto.request.auth.EmailAuthCheckRequestDto;
import com.project.back.dto.request.auth.EmailAuthRequestDto;
import com.project.back.dto.request.auth.FindIdEmailAuthRequestDto;
import com.project.back.dto.request.auth.FindIdRequestDto;
import com.project.back.dto.request.auth.FindPasswordRequestDto;
import com.project.back.dto.request.auth.FindPasswordResetRequestDto;
import com.project.back.dto.request.auth.IdCheckRequestDto;
import com.project.back.dto.request.auth.NicknameCheckRequestDto;
import com.project.back.dto.request.auth.SignInRequestDto;
import com.project.back.dto.request.auth.SignUpRequestDto;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.auth.FindIdResponseDto;
import com.project.back.dto.response.auth.SignInResponseDto;
import com.project.back.entity.EmailAuthNumberEntity;
import com.project.back.entity.UserEntity;
import com.project.back.provider.JwtProvider;
import com.project.back.provider.MailProvider;
import com.project.back.repository.EmailAuthNumberRepository;
import com.project.back.repository.UserRepository;
import com.project.back.service.AuthService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplementation implements AuthService {

    private final UserRepository userRepository;
    private final EmailAuthNumberRepository emailAuthNumberRepository;

    private final MailProvider mailProvider;
    private final JwtProvider jwtProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
        String accessToken = null;

        try {

            String userId = dto.getUserId();
            String password = dto.getPassword();
            UserEntity userEntity = userRepository.findByUserId(userId);

            if (userEntity == null)
                return ResponseDto.signInFailed();

            String encodedPassword = userEntity.getPassword();
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if (!isMatched)
                return ResponseDto.signInFailed();

            accessToken = jwtProvider.create(userId);
            if (accessToken == null)
                return ResponseDto.tokenCreationFailed();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignInResponseDto.success(accessToken);
    }

    @Override
    public ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto dto) {
        try {

            String userId = dto.getUserId();
            boolean existedUser = userRepository.existsById(userId);
            if (existedUser)
                return ResponseDto.duplicatedId();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> nicknameCheck(NicknameCheckRequestDto dto) {
        try {

            String Nickname = dto.getNickname();
            boolean existedUser = userRepository.existsByNickname(Nickname);
            if (existedUser)
                return ResponseDto.duplicatedNickname();

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

    public ResponseEntity<ResponseDto> findIdEmailAuth(FindIdEmailAuthRequestDto dto) {

        try {

            String userName = dto.getUserName();
            String userEmail = dto.getUserEmail();
            String authNumber = EmailAuthNumberUtil.createCodeNumber();

            UserEntity userEntity = userRepository.findByUserNameAndUserEmail(userName, userEmail);

            if (userEntity == null) {
                return ResponseDto.noExistUser();
            }

            // 이메일로 기존 authNumber 엔티티 조회
            EmailAuthNumberEntity emailAuthNumberEntity = emailAuthNumberRepository.findByUserEmail(userEmail);

            if (emailAuthNumberEntity != null) {
                // 존재하면 authNumber 업데이트
                emailAuthNumberEntity.setAuthNumber(authNumber);
                emailAuthNumberRepository.save(emailAuthNumberEntity);
            } else {
                // 존재하지 않으면 새로운 엔티티 생성
                emailAuthNumberEntity = new EmailAuthNumberEntity(userEmail, authNumber);
                emailAuthNumberRepository.save(emailAuthNumberEntity);
            }

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
    public ResponseEntity<ResponseDto> emailAuthCheck(EmailAuthCheckRequestDto dto) {
        try {

            String userEmail = dto.getUserEmail();
            String userNumber = dto.getAuthNumber();

            boolean isMatched = emailAuthNumberRepository.existsByUserEmailAndAuthNumber(userEmail, userNumber);
            if (!isMatched)
                return ResponseDto.authenticationFailed();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto) {
        try {

            String userId = dto.getUserId();
            String password = dto.getPassword();
            String Nickname = dto.getNickname();
            String userEmail = dto.getUserEmail();
            String authNumber = dto.getAuthNumber();

            boolean existedUser = userRepository.existsByUserId(userId);
            if (existedUser)
                return ResponseDto.duplicatedId();

            boolean existedNickname = userRepository.existsByNickname(Nickname);
            if (existedNickname)
                return ResponseDto.duplicatedNickname();

            boolean existedEmail = userRepository.existsByUserEmail(userEmail);
            if (existedEmail)
                return ResponseDto.duplicatedEmail();

            boolean isMatched = emailAuthNumberRepository.existsByUserEmailAndAuthNumber(userEmail, authNumber);
            if (!isMatched)
                return ResponseDto.authenticationFailed();

            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super FindIdResponseDto> findId(FindIdRequestDto dto) {

        try {
            String userName = dto.getUserName();
            String userEmail = dto.getUserEmail();
            String authNumber = dto.getAuthNumber();

            // 인증번호 검증
            boolean isMatched = emailAuthNumberRepository.existsByUserEmailAndAuthNumber(userEmail, authNumber);
            if (!isMatched) {
                return ResponseDto.authenticationFailed();
            }

            // 인증번호가 유효하다면 사용자 아이디를 조회
            UserEntity userEntity = userRepository.findByUserNameAndUserEmail(userName, userEmail);
            if (userEntity == null) {
                return ResponseDto.authenticationFailed();
            }

            String userId = userEntity.getUserId();
            return FindIdResponseDto.success(userId);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> findPassword(FindPasswordRequestDto dto) {

        try {

            String userId = dto.getUserId();
            String userName = dto.getUserName();
            String userEmail = dto.getUserEmail();
            String authNumber = dto.getAuthNumber();

            // 인증번호 검증
            boolean isMatched = emailAuthNumberRepository.existsByUserEmailAndAuthNumber(userEmail, authNumber);
            if (!isMatched) {
                return ResponseDto.authenticationFailed();
            }

            // 사용자 정보 검증
            boolean isUserMatched = userRepository.existsByUserIdAndUserNameAndUserEmail(userId, userName, userEmail);
            if (!isUserMatched) {
                return ResponseDto.authenticationFailed();
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> findPasswordReset(FindPasswordResetRequestDto dto, String userId) {
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
            userEntity.findPassword(dto);
            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

}