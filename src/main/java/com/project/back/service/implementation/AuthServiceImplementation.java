package com.project.back.service.implementation;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.back.common.util.EmailAuthNumberUtil;
import com.project.back.dto.request.auth.EmailAuthCheckRequestDto;
import com.project.back.dto.request.auth.EmailAuthRequestDto;
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
public class AuthServiceImplementation implements AuthService{
    
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

            if (userEntity == null) return ResponseDto.signInFailed();

            String encodedPassword = userEntity.getPassword();
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if (!isMatched) return ResponseDto.signInFailed();

            accessToken = jwtProvider.create(userId);
            if (accessToken == null) return ResponseDto.tokenCreationFailed();

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
            if (existedUser) return ResponseDto.duplicatedId();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> nickNameCheck(NicknameCheckRequestDto dto) {
        try {

            String nickName = dto.getNickName();
            boolean existedUser = userRepository.existsByNickname(nickName); 
            if (existedUser) return ResponseDto.duplicatedNickname();

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
            if (existedEmail) return ResponseDto.duplicatedEmail();

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
    public ResponseEntity<ResponseDto> emailAuthCheck(EmailAuthCheckRequestDto dto) {
        try {

            String userEmail = dto.getUserEmail();
            String userNumber = dto.getAuthNumber();

            boolean isMatched = emailAuthNumberRepository.existsByEmailAndAuthNumber(userEmail, userNumber);
            if (!isMatched) return ResponseDto.authenticationFailed();

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
            String nickName = dto.getNickname();
            String userEmail = dto.getUserEmail();
            String authNumber = dto.getAuthNumber();

            boolean existedUser = userRepository.existsByUserId(userId);
            if (existedUser) return ResponseDto.duplicatedId();

            boolean existedNickName = userRepository.existsByNickname(nickName);
            if (existedNickName) return ResponseDto.duplicatedNickname();

            boolean existedEmail = userRepository.existsByUserEmail(userEmail);
            if (existedEmail) return ResponseDto.duplicatedEmail();

            boolean isMatched = emailAuthNumberRepository.existsByEmailAndAuthNumber(userEmail, authNumber);    
            if (!isMatched) return ResponseDto.authenticationFailed();

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

           boolean isMatched = userRepository.existsByUserNameAndUserEmail(userName, userEmail);
           if (!isMatched) return ResponseDto.authenticationFailed();
            
            UserEntity userEntity = userRepository.findByUserNameAndUserEmail(userName ,userEmail);
            
            userRepository.save(userEntity);
            
            return FindIdResponseDto.success(userEntity);

        } catch(Exception exception) {
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

            boolean isMatched = userRepository.existsByUserIdAndUserNameAndUserEmail(userId, userName, userEmail);
            if (!isMatched) return ResponseDto.authenticationFailed();

        } catch(Exception exception) {
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
            if (userEntity == null) return ResponseDto.noExistUser();

            boolean isMatched = userRepository.existsById(userId);
            if (!isMatched) return ResponseDto.authenticationFailed();

            String encodedPassword = passwordEncoder.encode(password);

            dto.setPassword(encodedPassword);

            userEntity.findPassword(dto);

            userRepository.save(userEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }
    
}
