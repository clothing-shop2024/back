package com.project.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.back.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    boolean existsByUserId(String userId);

    boolean existsByNickname(String nickname);

    boolean existsByUserEmail(String userEmail);

    boolean existsByUserIdAndUserEmail(String userId, String userEmail);

    // 수정
    boolean existsByUserNameAndUserEmail(String userName, String userEmail);

    boolean existsByUserIdAndUserNameAndUserEmail(String userId, String userName, String userEmail);

    // 아이디/비밀번호 인증번호
    boolean existsByUserEmailAndSendAuthNumber(String userEmail, String sendAuthNumber);

    UserEntity findByUserId(String userId);

    UserEntity findByNickname(String nickname);

    UserEntity findUserPwByUserId(String userId);

    UserEntity findByUserEmail(String userEmail);

    UserEntity findByPassword(String Password);

    UserEntity findUserIdByUserEmail(String userEmail);

    UserEntity findByUserNameAndUserEmail(String userName, String userEmail);

    // board에 사용하려고
    UserEntity findUserRoleByUserId(String userId);
}